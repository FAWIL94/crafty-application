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

    $: {
        let searchParams = new URLSearchParams($querystring);

        if (searchParams.has("page")) {
            currentPage = searchParams.get("page");
        } else {
            currentPage = "1";
        }

        getCustomers();
    }

    function getCustomers() {
        let query = "pageSize=4&page=" + currentPage;

        var config = {
            method: "get",
            url: api_root + "/api/customer?" + query,
            headers: {},
        };

        axios(config)
            .then(function (response) {
                customers = response.data.content;
                nrOfPages = response.data.totalPages;
            })
            .catch(function (error) {
                alert("Could not get customers");
                console.log(error);
            });
    }

    

</script>

<h1>Übersicht aller Kunden</h1>

<table class="table">
    <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Email</th>
        </tr>
    </thead>
    <tbody>
        {#each customers as customer}
            <tr>
                <td>{customer.name}</td>
                <td>{customer.email}</td>
            </tr>
        {/each}
    </tbody>
</table>
<nav>
    <ul class="pagination">
        {#each Array(nrOfPages) as _, i}
            <li class="page-item">
                <a
                    class="page-link"
                    class:active={currentPage == i + 1}
                    href={"#/customersoverview?page=" + (i + 1)}>{i + 1}</a
                >
            </li>
        {/each}
    </ul>
</nav>
