document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector('form');
    const input = document.getElementById("jobTitleInput");
    const hint = document.getElementById("jobTitleHint");

    if (input && hint) {
        input.addEventListener('input', function() {
            let text = this.value;

            if (text == "") {
                hint.textContent = "Введите должность сотрудника";
            } else {
                hint.textContent = "Акт будет оформлен на: " + text;
            }
        });
    }
    if (form) {
        form.addEventListener('submit', function() {
            const btn = document.getElementById('submitBtn');
            const loader = document.getElementById('btnLoader');

            if (btn && loader) {
                btn.classList.add('loading');
                loader.style.display = 'block';
                setTimeout(function() {
                    btn.classList.remove('loading');
                    loader.style.display = 'none';
                }, 1000);
            }
        });
    }
});