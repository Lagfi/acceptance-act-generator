document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector('form');
    const dataListWarehouse = document.getElementById("positionsData");
    fetch("http://localhost:63412/api/positions")
        .then(response => response.json())
        .then(data => {
            dataListWarehouse.innerHTML = "";
            data.forEach(position => {
                const option = document.createElement("option")
                let prettyName = position.charAt(0).toUpperCase() + position.slice(1);
                option.textContent = prettyName;
                dataListWarehouse.appendChild(option);
            });
            console.log("Данные с бэкапа прилетели:", data);
        })
        .catch(error => {
            console.error("Косяк сети:", error);
            dataListWarehouse.innerHTML = "<option>Ошибка загрузки должностей</option>";
        });
    if (form) {
        form.addEventListener('submit', function() {
            const btn = document.getElementById('submitBtn');
            const loader = document.getElementById('btnLoader');
            if (btn && loader) {
                // 1. Включаем анимацию загрузки
                btn.classList.add('loading');
                loader.style.display = 'block';
                setTimeout(function() {
                    btn.classList.remove('loading');
                    loader.style.display = 'none';
                }, 2000);
            }
        });
    }
});