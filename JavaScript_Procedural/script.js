
function calculateScore() {
    let name = document.getElementById("name").value;
    let subject = document.getElementById("subject").value;
    let total = document.getElementById("total").value;
    let correct = document.getElementById("correct").value;

    if (total.trim() === "" || correct.trim() === "") {
        alert("Please enter valid numbers.");
        return;
    }

    total = Number(total);
    correct = Number(correct);

    if (!Number.isInteger(total) || !Number.isInteger(correct)) {
        alert("Please enter valid numbers.");
        return;
    }

    if (total <= 0 || correct < 0 || correct > total ||
        name.trim() === "" || subject.trim() === "") {
        alert("Please enter valid information.");
        return;
    }

    let percentage = (correct * 100) / total;
    let status = percentage >= 75 ? "PASSED" : "FAILED";

    document.getElementById("result").innerText =
        percentage.toFixed(2) + "% - " + status;
}

function clearForm() {
    document.getElementById("name").value = "";
    document.getElementById("subject").value = "";
    document.getElementById("total").value = "";
    document.getElementById("correct").value = "";

    document.getElementById("result").innerText =
        "Result will appear here";
}
