$(document).ready(function () {
    var instructorSubjects = [];

    $.getJSON("/api/instructor/1/subject/not/list", function (data) {
        fullfillSubjectsList(data);
    });

    $.getJSON("/api/instructor/1/subject/list", function (data) {
        fullfillInstructorSubjectsList(data);
    });

    function fullfillSubjectsList(data) {
        $.map(data, function (subject, i) {
            instructorSubjects.push(subject);
            $("#subject-dd-list").append("<li>");
            $("#subject-dd-list li").last().addClass("dd-item").attr("data-id", i).append("<div>");
            $("#subject-dd-list div").last().addClass("dd-handle").text(subject.name + " - " + subject.subjectType.name).append("<span>");
            $("#subject-dd-list span").last().addClass("pull-right").append("<a>");
            $("#subject-dd-list a").last().data("subject", subject).attr("title", "Dodaj Przedmiot").addClass("text-success").append("<i>").on("click", function (e) {
                console.log($(this).data("subject"));
            });
            $("#subject-dd-list i").last().addClass("fa fa-plus");
            $("#subject-dd0list .dd-handle").on("mousedown", function (e) {
                e.preventDefault();
                return false;
            });
        });
    }

    function fullfillInstructorSubjectsList(data) {
        $.map(data, function (subject, i) {
            instructorSubjects.push(subject);
            $("#instructor-dd-list").append("<li>");
            $("#instructor-dd-list li").last().addClass("dd-item").attr("data-id", i).append("<div>");
            $("#instructor-dd-list div").last().addClass("dd-handle").text(subject.name + " - " + subject.subjectType.name).append("<span>");
            $("#instructor-dd-list span").last().addClass("pull-right").append("<a>");
            $("#instructor-dd-list a").last().data("subject", subject).attr("title", "Usuń Przedmiot").addClass("text-danger").append("<i>").on("click", function (e) {
                console.log($(this).data("subject"));
            });
            $("#instructor-dd-list i").last().addClass("fa fa-minus");
            $("#instructor-dd-list .dd-handle").on("mousedown", function (e) {
                e.preventDefault();
                return false;
            });
        });
    }
});
