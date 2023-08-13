let dailyClassChart = null;
let selectedBcaName = "요리/공예";

function createDailyClassChart(labels, values) {
    var ctx = document.getElementById('dailyClassChart').getContext('2d');

    if (dailyClassChart) {
        dailyClassChart.destroy(); 
    }

    dailyClassChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                data: values,
                label: '신규 클래스 개설 추이',
                backgroundColor: 'rgba(140, 90, 210, 0.2)',
                borderColor: 'rgba(140, 90, 210, 1)',
                borderWidth: 1,
                fill: {
                    target: "start",
                    below: 'rgba(255, 99, 132, 0.2)'
                },
                smooth: true
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

function dailyClassData() {
    let startDate = document.getElementById('startDateCl').value;
    let endDate = document.getElementById('endDateCl').value;

    $.ajax({
        url: '/admin/dailyClass?startDate=' + startDate + '&endDate=' + endDate + '&bcaname=' + selectedBcaName,
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            let labels = data.map(item => item.clregdate);
            let values = data.map(item => item.countclno);

            createDailyClassChart(labels, values);
        },
        error: function (error) {
            console.error('Error fetching data:', error);
        }
    });
}

const defaultStartDateCl = '2023-08-01';
const defaultEndDateCl = '2023-08-14';
document.getElementById('startDateCl').value = defaultStartDateCl;
document.getElementById('endDateCl').value = defaultEndDateCl;

dailyClassData();

document.querySelectorAll('.dropdown-menu a').forEach(function(element) {
    element.addEventListener('click', function() {
        selectedBcaName = this.textContent;
        document.getElementById('dropdownBtn').textContent = selectedBcaName;
        dailyClassData();
    });
});