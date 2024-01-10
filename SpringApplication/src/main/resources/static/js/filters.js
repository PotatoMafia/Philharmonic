$(document).ready(function () {
    $('[data-filter-class]').each(function () {
        var filterClass = $(this).data('filter-class');
        $(this).addClass(filterClass);
    });

function getFilterClass(date) {
var today = new Date();
today.setHours(0, 0, 0, 0);

var eventDate = new Date(date);
eventDate.setHours(0, 0, 0, 0);

    if (eventDate.getTime() === today.getTime()) {
    return 'today';
} else if (eventDate > today && eventDate <= addDays(today, 1)) {
    return 'tomorrow';
} else if (eventDate > today && eventDate <= addDays(today, 7)) {
    return 'nextWeek';
} else if (eventDate > today && eventDate <= addDays(today, 30)) {
    return 'nextMonth';
} else {
    return '';
}
}

    function addDays(date, days) {
    var result = new Date(date);
    result.setDate(result.getDate() + days);
    return result;
}

    $(document).ready(function () {
    $('.filters_menu li').click(function () {
        var filter = $(this).attr('data-filter');

        if (filter === '*') {
            $('.grid .col-sm-6').show();
        } else {
            $('.grid .col-sm-6').hide();
            $('.grid .col-sm-6' + filter).show();
        }

        $('.filters_menu li').removeClass('active');
        $(this).addClass('active');
    });
});
});

