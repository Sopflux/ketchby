
    let dailyUsersChart = null;

    function createDailyUsersChart(labels, values) {
        var ctx = document.getElementById('dailyUsersChart').getContext('2d');
        
		//차트가 다른 값으로 변경될 때 기존 차트 지우고 새로 덮어씀
        if (dailyUsersChart) {
            dailyUsersChart.destroy(); // Destroy the previous chart if it exists
        }
		
		
		// 차트 그리기
        dailyUsersChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [{
                    data: values,
                    label: '날짜별 신규 가입자',
                    backgroundColor: 'rgba(0, 131, 117, 0.2)', 
            		borderColor: 'rgba(0, 131, 117, 1)', 
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                scales: {
                    x: {
                        type: 'time',
                        time: {
                            unit: 'day',
                            displayFormats: {
                                day: 'MM-dd',
                            },
                        },
                        grid: {
		                    color: 'rgba(128, 128, 128, 0.1)', 
		                },
                        
                    },
                    y: {
                        grid: {
		                    color: 'rgba(128, 128, 128, 0.1)', 
		                },
                    },
                    
                },
            },
        });
    }
	
	/*
	*	dailyUsersData 정의
	*	매개변수로 가져온 날짜 선언 및 ajax 통신
	*/
    function dailyUsersData() {
        var startDate = document.getElementById("startDate").value;
        var endDate = document.getElementById("endDate").value;
        
        console.log("startDate:", startDate);
    	console.log("endDate:", endDate);

        $.ajax({
            url: "/admin/dailyUsers?startDate=" + startDate + "&endDate=" + endDate,
            type: "GET",
            dataType: "json",
            success: function (data) {
                
				// 데이터 값으로부터 labels와 values 값을 가져옴
                let labels = data.map(item => item.regdate);
                let values = data.map(item => item.countaid);

                // count 값(*aid=String)을 String 에서 Integer로 변환
                values = values.map(value => parseInt(value, 10));

                console.log("Labels:", labels);
                console.log("Values:", values);

                // 그린 차트에 labels와 values를 fetch해 불러옴
                createDailyUsersChart(labels, values);
            },
            error: function (error) {
                console.log("에러발생:", error);
            }
        });
    }

   
   //dafault 값 생성 후 페이지 열 때 뜨도록 함
   const defaultStartDate = "2023-08-01";
   const defaultEndDate = "2023-08-07";
   document.getElementById("startDate").value = defaultStartDate;
   document.getElementById("endDate").value = defaultEndDate;
   
    dailyUsersData();

