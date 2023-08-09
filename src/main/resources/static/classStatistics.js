let classByBcaChart = null;

    function createClassByBcaChart(labels, values) {
        var ctx = document.getElementById('classByBcaChart').getContext('2d');

		//차트가 다른 값으로 변경될 때 기존 차트 지우고 새로 덮어씀
        if (classByBcaChart) {
            classByBcaChart.destroy(); // Destroy the previous chart if it exists
        }


		// 차트 그리기
        classByBcaChart = new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: labels,
                datasets: [{
                    data: values,
                    backgroundColor: [
					  'rgb(255, 99, 132)',
				      'rgb(54, 162, 235)',
				      'rgb(255, 205, 86)',
				      'rgb(75, 192, 192)',
				      'rgb(153, 102, 255)'
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
    function classByBcaData() {

        $.ajax({
            url: "/admin/classByBca",
            type: "GET",
            dataType: "json",
            success: function (data) {

				// 데이터 값으로부터 labels와 values 값을 가져옴
				let labels = data.map(item => item.bcaname);
			    let values = data.map(item => item.countclno);


                console.log("Labels:", labels);
                console.log("Values:", values);

                // 그린 차트에 labels와 values를 fetch해 불러옴
                createClassByBcaChart(labels, values);
            },
            error: function (error) {
                console.log("에러발생:", error);
            }
        });
    }

    classByBcaData();