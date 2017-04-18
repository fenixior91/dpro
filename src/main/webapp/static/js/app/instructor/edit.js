$(document).ready(function () {
    var instructorSubjects = [];

    $("#subject-dd").nestable({
        maxDepth: 1,
        group: 1
    }).on("dragMove", function (event, item, source, destination) {
        console.log("SUBJECT");
    });

    $("#instructor-dd").nestable({
        maxDepth: 1,
        group: 1
    }).on("dragMove", function (event, item, source, destination) {
        console.log("INSTRUCTOR");
    });

    $.ajax({
        url: "/subject/list",
        success: function (data) {
            $.map(data, function (subject, i) {
                instructorSubjects.push(subject);

                $("#subject-dd-list").append("<li>");
                $("#subject-dd-list li").last().addClass("dd-item").attr("data-id", i).append("<div>");
                $("#subject-dd-list div").last().addClass("dd-handle").text(subject.name + " - " + subject.subjectType.name).append("<span>");

                $("#subject-dd-list span").last().addClass("pull-right").append("<a>");
                $("#subject-dd-list a").last().attr("title", "Dodaj Przedmiot").addClass("text-success").append("<i>").on("click", function (e) {
                    console.log("Dodawanie");
                });

                $("#subject-dd-list i").last().addClass("fa fa-plus");

                $(".dd-handle").on("mousedown", function (e) {
                    e.preventDefault();

                    return false;
                });


                console.log(instructorSubjects);
            });
        }
    });
});
