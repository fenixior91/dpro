Vue.component("todo-item", {
    props: ["todo"],
    template: "<li>{{todo.text}}</li>"
});

var app = new Vue({
    el: "#app",
    data: {
        groceryList: [
            { text: "Vegetables"},
            { text: "Cheese" },
            { text: "Whatever else humans are supposed to eat" }
        ]
    }
});