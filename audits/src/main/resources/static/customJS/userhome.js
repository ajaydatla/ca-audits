$(function () {
    $(".sidebar-list").mouseenter(function () {
        var eid = $(this).attr('id');
        console.log("called fddun d ", eid);
        var addele = "<ul class='nav nav-pills nav-stacked sidebar-dropdown'>"+
        "<li><a href='#'>hello</a></li><li><a href='#'>world</a></li></ul>";
        $("#inner-" + eid).append(addele);
        $("#inner-" + eid).show();
    });
    $(".sidebar-list").mouseleave(function () {
        var eid = $(this).attr('id');
        $("#inner-" + eid).empty();
    });
});