<script>
    import axios from "axios";

    import { querystring } from "svelte-spa-router";

    const api_root = "http://localhost:8080";

    let currentPage;
    let nrOfPages = 0;

    let customers = [];
    let customer = {
        email: null,
        name: null
    };

    
    function createCustomer() {
        var config = {
            method: "post",
            url: api_root + "/api/customer",
            headers: {
                "Content-Type": "application/json",
            },
            data: customer,
        };

        axios(config)
            .then(function (response) {
                alert("Customer created");
                getCustomers();
            })
            .catch(function (error) {
                alert("Could not create Customer");
                console.log(error);
            });
    }

</script>

<h1 class="mt-3">Neuen Kunden erfassen</h1>
<form class="mb-5">
    <div class="row mb-3">
        <div class="col">
            <label class="form-label" for="name">Name</label>
            <input
                bind:value={customer.name}
                class="form-control"
                id="name"
                type="text"
            />
        </div>
    </div>
    <div class="row mb-3">
        <div class="col">
            <label class="form-label" for="email">Email</label>
            <input
                bind:value={customer.email}
                class="form-control"
                id="email"
                type="text"
            />
        </div>
    </div>
    <button type="button" class="btn btn-warning" on:click={createCustomer}
        >Senden</button
    >
</form>
