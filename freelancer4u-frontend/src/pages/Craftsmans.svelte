<script>
    import axios from "axios";

    import { querystring } from "svelte-spa-router";

    const api_root = "http://localhost:8080";

    let currentPage;
    let nrOfPages = 0;

    let craftsmans = [];
    let craftsman = {
        companyName: null,
        street: null,
        plz: null,
        city: null,

    };

    function createCraftsman() {
        var config = {
            method: "post",
            url: api_root + "/api/craftsman",
            headers: {
                "Content-Type": "application/json",
            },
            data: craftsman,
        };

        axios(config)
            .then(function (response) {
                alert("Customer created");
                getCraftsmans();
            })
            .catch(function (error) {
                alert("Could not create Craftsman");
                console.log(error);
            });
    }

</script>

<h1 class="mt-3">Neuen Handwerker erfassen</h1>
<form class="mb-5">
    <div class="row mb-3">
        <div class="col">
            <label class="form-label" for="CompanyName">Firmenname</label>
            <input
                bind:value={craftsman.companyName}
                class="form-control"
                id="CompanyName"
                type="text"
            />
        </div>
    </div>
    <div class="row mb-3">
        <div class="col">
            <label class="form-label" for="address">Firmenadresse</label>
            <input
                bind:value={craftsman.street}
                class="form-control"
                id="address"
                type="text"
            />
        </div>
    </div>
    <div class="row mb-3">
        <div class="col">
            <label class="form-label" for="plz">PLZ</label>
            <input
                bind:value={craftsman.plz}
                class="form-control"
                id="plz"
                type="text"
            />
        </div>
    </div>
    <div class="row mb-3">
        <div class="col">
            <label class="form-label" for="city">Ort</label>
            <input
                bind:value={craftsman.city}
                class="form-control"
                id="city"
                type="text"
            />
        </div>
    </div>
    <button type="button" class="btn btn-warning" on:click={createCraftsman}
        >Submit</button
    >
</form>
