$(document).ready(function () {
    var studentSubjects = [];
    var studentId = $("input#id").val();

    $.getJSON("/api/student/" + studentId + "/subject/not/list", function (data) {
        $.map(data, function (subject) {
            addSubject("all", subject);
        });
    });


    $.getJSON("/api/student/" + studentId + "/subject/list", function (data) {
        $.map(data, function (subject) {
            studentSubjects.push(subject);
            addSubject("student", subject);
        });
    });

    $("#attach_subject button[type='submit']").on("click", function (e) {
        e.preventDefault();

        var token = $("input[name='_csrf']").val();

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/student/" + studentId + "/subject/attach",
            data: JSON.stringify(studentSubjects),
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
        var listId = (list === "all") ? "#subject-dd-list" : "#student-dd-list";
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
                addSubject("student", subject);
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
        var index = studentSubjects.indexOf(subject);
        studentSubjects.splice(index, 1);
    }

    function attachSubjects(subject) {
        studentSubjects.push(subject);
    }
});
