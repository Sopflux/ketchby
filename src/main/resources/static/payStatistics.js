
    let dailyPaymentChart = null;

    function createDailyPaymentChart(labels, values) {
        var ctx = document.getElementById('dailyPaymentChart').getContext('2d');
        
		//차트가 다른 값으로 변경될 때 기존 차트 지우고 새로 덮어씀
        if (dailyPaymentChart) {
            dailyPaymentChart.destroy(); 
        }
		
		
		// 차트 그리기
        dailyPaymentChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: labels,
                datasets: [{
                    data: values,
                    label: '신규 결제/예약 추이',
                    backgroundColor: 'rgba(255, 159, 64, 0.2)', 
            		borderColor: 'rgba(255, 159, 64, 1)', 
                    borderWidth: 1,
                    fill: {
                    target: "start",
                    below: 'rgba(255, 159, 64, 0.2)'
                },
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
						beginAtZero: true,
                        grid: {
		                    color: 'rgba(128, 128, 128, 0.1)', 
		                },
                    },
                    
                },
            },
        });
    }
	
	/*
	*	dailyPaymentData 정의
	*	매개변수로 가져온 날짜 선언 및 ajax 통신
	*/
    function dailyPaymentData() {
        var startDate = document.getElementById("startDatePay").value;
        var endDate = document.getElementById("endDatePay").value;
        
        console.log("startDatePay:", startDate);
    	console.log("endDatePay:", endDate);

        $.ajax({
            url: "/admin/dailyPayment?startDate=" + startDate + "&endDate=" + endDate,
            type: "GET",
            dataType: "json",
            success: function (data) {
                
				// 데이터 값으로부터 labels와 values 값을 가져옴
                let labels = data.map(item => item.paydate);
                let values = data.map(item => item.countpayno);

                // count 값(*aid=String)을 String 에서 Integer로 변환
                values = values.map(value => parseInt(value, 10));

                // 그린 차트에 labels와 values를 fetch해 불러옴
                createDailyPaymentChart(labels, values);
            },
            error: function (error) {
                console.log("에러발생:", error);
            }
        });
    }

   
   //dafault 값 생성 후 페이지 열 때 뜨도록 함
   const defaultStartDatePay = "2023-08-01";
   const defaultEndDatePay = "2023-08-14";
   document.getElementById("startDatePay").value = defaultStartDatePay;
   document.getElementById("endDatePay").value = defaultEndDatePay;
   
    dailyPaymentData();

