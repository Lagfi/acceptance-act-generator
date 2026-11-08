document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector('form');
    const dataListWarehouse = document.getElementById("positionsData");
    const dataListMolWarehouse = document.getElementById("molPositionsData");

    fetch("/api/positions")
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
            console.error("Косяк сети 1:", error);
            dataListWarehouse.innerHTML = "<option>Ошибка загрузки должностей пользователя</option>";
        });
    fetch("/api/molpositions")
        .then(molresponse => molresponse.json())
        .then(moldata => {
            dataListMolWarehouse.innerHTML = "";
            moldata.forEach(molposition => {
                const option = document.createElement("option")
                let prettyNameMol = molposition.charAt(0).toUpperCase() + molposition.slice(1);
                option.textContent = prettyNameMol;
                dataListMolWarehouse.appendChild(option);
            });
            console.log("Данные с бэкапа прилетели:", moldata);
        })
        .catch(error => {
            console.error("Косяк сети 1:", error);
            dataListMolWarehouse.innerHTML = "<option>Ошибка загрузки должностей мат.ответственных</option>";
        });
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
                }, 3000);
            }
        });
    }
});