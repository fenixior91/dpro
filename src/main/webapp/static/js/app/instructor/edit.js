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
            addSubjectToList(subject, false);
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
            data: JSON.stringify({a: "x", b: "b"}),
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

    function addSubjectToList(subject, isNew) {
        instructorSubjects.push({
            id: subject.id,
            name: subject.name,
            isNew: isNew,
            isAttached: true
        });
    }

    function detachSubjects(subject) {
        instructorSubjects.forEach(function (v) {
            if (v.id === subject.id) {
                v.isAttached = false;
            }
        });
    }

    function attachSubjects(subject) {
        instructorSubjects.forEach(function (v) {
            if (v.id === subject.id) {
                console.log(v);
            }
        });
    }
});
