	
    let levelByUserChart = null;

    function createLevelByUserChart(labels, values) {
        var ctx = document.getElementById('levelByUserChart').getContext('2d');
        
		//차트가 다른 값으로 변경될 때 기존 차트 지우고 새로 덮어씀
        if (levelByUserChart) {
            levelByUserChart.destroy(); // Destroy the previous chart if it exists
        }
		
		
		// 차트 그리기
        levelByUserChart = new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: labels,
                datasets: [{
                    data: values,
                    backgroundColor: [
				      'rgba(255, 99, 132, 0.5)',    
				      'rgba(255, 205, 86, 0.5)',   
				      'rgba(54, 162, 235, 0.5)'
				      ],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
            },
        });
    }
	
	/*
	*	dailyUsersData 정의
	*	매개변수로 가져온 날짜 선언 및 ajax 통신
	*/
    function levelByUserData() {

        $.ajax({
            url: "/admin/levelByUser",
            type: "GET",
            dataType: "json",
            success: function (data) {
				
				// 데이터 값으로부터 labels와 values 값을 가져옴
				let labels = data.map(item => item.level_);
			    let values = data.map(item => item.countaid);


                // 그린 차트에 labels와 values를 fetch해 불러옴
                createLevelByUserChart(labels, values);
            },
            error: function (error) {
                console.log("에러발생:", error);
            }
        });
    }

 
    levelByUserData();

