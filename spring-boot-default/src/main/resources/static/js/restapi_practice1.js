document.addEventListener('DOMContentLoaded', function() {
    // 연도 옵션 채우기
    var yearSelect = document.getElementById('yearSelect');
    var currentYear = new Date().getFullYear();
    var startYear = currentYear - 100; // 100년 전부터 현재까지
    for (var year = startYear; year <= currentYear; year++) {
        var option = document.createElement('option');
        option.value = year;
        option.text = year;
        yearSelect.appendChild(option);
    }

    // 월 옵션 채우기
    var monthSelect = document.getElementById('monthSelect');
    for (var month = 1; month <= 12; month++) {
        var option = document.createElement('option');
        option.value = month;
        option.text = month + '월';
        monthSelect.appendChild(option);
    }

    // 일 옵션 채우기
    var daySelect = document.getElementById('daySelect');
    for (var day = 1; day <= 31; day++) {
        var option = document.createElement('option');
        option.value = day;
        option.text = day + '일';
        daySelect.appendChild(option);
    }
});