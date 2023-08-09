	
    let quitReasonChart = null;

    function createQuitReasonChart(labels, values) {
        var ctx = document.getElementById('quitReasonChart').getContext('2d');
        
		//차트가 다른 값으로 변경될 때 기존 차트 지우고 새로 덮어씀
        if (quitReasonChart) {
            quitReasonChart.destroy(); 
        }
		
		
		// 차트 그리기
         quitReasonChart = new Chart(ctx, {
        type: 'radar',
        data: {
            labels: labels,
            datasets: [{
                data: values,
                backgroundColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                r: {
                	suggestedMin: 0,
                    suggestedMax: 5,
                    stepSize: 1,
                    grid: {
                        color: 'rgba(128, 128, 128, 1)',
                    },
                    ticks: {
                        beginAtZero: true,
                        color: 'rgba(128, 128, 128, 1)',
                        showLabelBackdrop: false // hide square behind text // 이부분이 숫자 label의 배경 부분.
                    },
                    angleLines: {
                        color: 'rgba(128, 128, 128, 1)' // 중심에서 라벨까지 줄 색상
                    },
                    pointLabels: {
                        color: 'rgba(128, 128, 128, 1)', // 라벨의 글씨 색상
                    }
                },
            },
            plugins:{
                legend:{
                    display:false // 최상단 라벨의 카테고리 안보이기.
                }
            }
        },
    });
}

	
	/*
	*	dailyUsersData 정의
	*	매개변수로 가져온 날짜 선언 및 ajax 통신
	*/
    function quitReasonData() {

        $.ajax({
            url: "/admin/quitReason",
            type: "GET",
            dataType: "json",
            success: function (data) {
				
				// 데이터 값으로부터 labels와 values 값을 가져옴
				let labels = data.map(item => item.rereason);
			    let values = data.map(item => item.countquit);


                console.log("Labels:", labels);
                console.log("Values:", values);

                // 그린 차트에 labels와 values를 fetch해 불러옴
                createQuitReasonChart(labels, values);
            },
            error: function (error) {
                console.log("에러발생:", error);
            }
        });
    }


    quitReasonData();


