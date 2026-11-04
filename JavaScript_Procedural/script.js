
let history = [];

function calculateScore() {

    let name = document.getElementById("name").value;
    let subject = document.getElementById("subject").value;
    let total = document.getElementById("total").value;
    let correct = document.getElementById("correct").value;

    if (total.trim() === "" || correct.trim() === "") {
        alert("Please enter valid numbers.");
        return;
    }

    let t = Number(total);
    let c = Number(correct);

    if (!Number.isInteger(t) || !Number.isInteger(c)) {
        alert("Please enter valid numbers.");
        return;
    }

    if (t <= 0 || c < 0 || c > t ||
        name.trim() === "" || subject.trim() === "") {
        alert("Please enter valid information.");
        return;
    }

    let percentage = (c * 100) / t;
    let status = percentage >= 75 ? "PASSED" : "FAILED";

    let score = percentage.toFixed(2) + "% - " + status;

    document.getElementById("result").innerText = score;

    let record = {
        name: name,
        subject: subject,
        score: score
    };

    history.push(record);

    displayHistory();
}

function displayHistory() {

    let output = "";

    for (let i = 0; i < history.length; i++) {

        output += `
            <div class="history-item">
                <input type="checkbox" class="history-check" value="${i}">
                ${history[i].name} | ${history[i].subject} | ${history[i].score}
            </div>
        `;
    }

    document.getElementById("history").innerHTML = output;
}

function clearForm() {

    document.getElementById("name").value = "";
    document.getElementById("subject").value = "";
    document.getElementById("total").value = "";
    document.getElementById("correct").value = "";

    document.getElementById("result").innerText =
        "Result will appear here";
}

function deleteSelected() {

    let selected = document.querySelectorAll(".history-check:checked");

    if (selected.length === 0) {
        alert("Please select a history record.");
        return;
    }

    let indexes = [];

    selected.forEach(item => {
        indexes.push(Number(item.value));
    });

    indexes.sort((a, b) => b - a);

    indexes.forEach(index => {
        history.splice(index, 1);
    });

    displayHistory();
}

function deleteAll() {

    if (history.length === 0) {
        alert("No history records to delete.");
        return;
    }

    let confirmDelete = confirm(
        "Are you sure you want to delete all history?"
    );

    if (confirmDelete) {
        history = [];
        displayHistory();
    }
}
