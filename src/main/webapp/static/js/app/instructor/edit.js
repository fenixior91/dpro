$(document).ready(function () {
    var instructorSubjects = [];
    var instructorId = $("input#id").val();

    $.getJSON("/api/instructor/" + instructorId + "/subject/not/list", function (data) {
        $.map(data, function (subject) {
            addSubject("all", subject);
        });
    });


    $.getJSON("/api/instructor/" + instructorId + "/subject/list", function (data) {
        $.map(data, function (subject) {
            instructorSubjects.push(subject);
            addSubject("instructor", subject);
        });
    });

    $("#attach_subject button[type='submit']").on("click", function (e) {
        e.preventDefault();

        var token = $("input[name='_csrf']").val();

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/instructor/" + instructorId + "/subject/attach",
            data: JSON.stringify(instructorSubjects),
            dataType: "json",
            headers: {"X-CSRF-TOKEN": token},
            success: function (data) {
                console.log(data);
            },
            error: function (error) {
                console.log(error);
            }
        });
    });

    function addSubject(list, subject) {
        var listId = (list === "all") ? "#subject-dd-list" : "#instructor-dd-list";
        var title = (list === "all") ? "Dodaj Przedmiot" : "Usuń Przedmiot";
        var buttonColorClass = (list === "all" ? "text-success" : "text-danger");
        var iconClass = (list === "all" ? "fa fa-plus" : "fa fa-minus");

        $(listId).append("<li>");
        $(listId + " li").last().addClass("dd-item").attr("data-id", subject.id).append("<div>");
        $(listId + " div").last().addClass("dd-handle").text(subject.name + " - " + subject.subjectType.name).append("<span>");
        $(listId + " span").last().addClass("pull-right").append("<a>");
        $(listId + " a").last().data("subject", subject).attr("title", title).addClass(buttonColorClass).append("<i>").on("click", function (e) {
            var subject = $(this).data("subject");

            if (list === "all") {
                addSubject("instructor", subject);
                attachSubjects(subject);
            } else {
                addSubject("all", subject);
                detachSubjects(subject);
            }

            $(listId).find("[data-id='" + subject.id + "']").detach();
        });
        $(listId + " i").last().addClass(iconClass);
        $(listId + " .dd-handle").on("mousedown", function (e) {
            e.preventDefault();
            return false;
        });
    }

    function detachSubjects(subject) {
        var index = instructorSubjects.indexOf(subject);
        instructorSubjects.splice(index, 1);
    }

    function attachSubjects(subject) {
        instructorSubjects.push(subject);
    }
});
