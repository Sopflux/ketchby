package com.example.demo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.FieldValue;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobId;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;

/**
 * 구글 빅쿼리
 */
public class ApiBigQueryAuthentication {

    /**
     * 구글 credentials.json을 통한 Biquery 생성
     * @return {BigQuery}
     */
	public static BigQuery getBigQuery() {
	    try {
	        // credentials.json 파일을 읽는다.
	        ClassPathResource resource = new ClassPathResource("keys/ketchby-313a6aa18a42.json");
	        GoogleCredentials credentials;
	        try (InputStream inputStream = resource.getInputStream()) {
	            credentials = ServiceAccountCredentials.fromStream(inputStream);
	        }

	        // BigQuery 생성
	        BigQuery bigQuery = BigQueryOptions.newBuilder()
	                .setCredentials(credentials)
	                // credentials.json에 있는 project_id
	                .setProjectId("ketchby")
	                .build()
	                .getService();

	        System.out.println("bigquery 생성 완료");
	        return bigQuery;
	    } catch (Exception e) {
	        System.out.println("예외발생:"+e.getMessage());
	        return null;
	    }
	}
    
    
    
    /**
     * BigQuery 수행 및 결과
     * @param {String} query
     * @throws {Exception}
     * @return {TableResult}
     */
    public static TableResult bigQueryExecute(String query) throws Exception {
        // BigQuery 객체 가져오기
        BigQuery bigQuery = getBigQuery();
        
        // 쿼리를 실행하기 위한 Job 구성 설정
        QueryJobConfiguration queryJobConfiguration = 
            // Query
            QueryJobConfiguration.newBuilder(query)
                // 표준 SQL 사용
                // See: https://cloud.google.com/bigquery/sql-reference/
                .setUseLegacySql(false)
            .build();
        
        // 보안문제로 JobId 생성
        // 무작위로 생성된 UUID를 사용하여 고유한 JobId 생성
        JobId jobId = JobId.of(UUID.randomUUID().toString());
        
        // BigQuery에 Job 생성 및 설정된 JobId 할당
        Job job = bigQuery.create(JobInfo.newBuilder(queryJobConfiguration).setJobId(jobId).build());
        
        // 쿼리가 완료될 때까지 대기
        job = job.waitFor();
        
        System.out.println("bigquery 쿼리 생성 완료");
        // 예외처리
        if (job == null) {
            throw new RuntimeException("쿼리가 존재하지 않습니다.");
        } else {
            // Job의 상태에 에러가 있는지 확인하고, 에러가 있으면 에러 메시지를 포함한 RuntimeException을 발생	
            if (job.getStatus().getError() != null) {
                String errorMessage =
                    job.getStatus().getError().getMessage() + "\n"
                    + job.getStatus().getError().getReason() + "\n"
                    + job.getStatus().getError().toString();
                
                throw new RuntimeException(errorMessage);
            } else {
                // 에러가 없으면 Job의 쿼리 결과를 가져와서 TableResult 객체로 반환
                TableResult tableResult = job.getQueryResults();
                return tableResult;
            }
        }
    }



    /**
     * 빅쿼리 조회
     * @return {List<Map<String, Object>>}
     * @throws {Exception}
     */
 // ...

    public List<Map<String, Object>> selectBigQuery() throws Exception {
        System.out.println("빅쿼리 조회 시작");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("SELECT geo FROM `ketchby.analytics_397498531.events_20230721` LIMIT 1000");

        TableResult tableResult = bigQueryExecute(stringBuffer.toString());
        // TableResults를 담을 List 생성
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        
        // tableResult를 순회하며 처리
        for (FieldValueList fieldValueList : tableResult.iterateAll()) {
        	// 각 행마다 맵 초기화
            Map<String, Object> map = new HashMap<String, Object>();
            
            // 행에서 "geo" 필드 값을 가져옴
            FieldValue fieldValue = fieldValueList.get("geo");

            // "geo" 필드에서 "country"와 "region" 값을 추출
            String country = fieldValue.getRecordValue().get("country").getStringValue();
            String region = fieldValue.getRecordValue().get("region").getStringValue();

            // 추출한 값을 맵에 적절한 키로 넣음
            map.put("country", country);
            map.put("region", region);


            // 맵을 리스트에 추가
            dataList.add(map);
        } // tableResult 순회 끝

        return dataList;
    }

    

}