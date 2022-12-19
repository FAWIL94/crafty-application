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

    $: {
        let searchParams = new URLSearchParams($querystring);

        if (searchParams.has("page")) {
            currentPage = searchParams.get("page");
        } else {
            currentPage = "1";
        }

        getCraftsmans();
    }

    function getCraftsmans() {
        let query = "pageSize=4&page=" + currentPage;

        var config = {
            method: "get",
            url: api_root + "/api/craftsman?" + query,
            headers: {},
        };

        axios(config)
            .then(function (response) {
                craftsmans = response.data.content;
                nrOfPages = response.data.totalPages;
            })
            .catch(function (error) {
                alert("Could not get craftsmans");
                console.log(error);
            });
    }

   
</script>

<h1>Übersicht aller Handwerker</h1>

<table class="table">
    <thead>
        <tr>
            <th scope="col">Firmenname</th>
            <th scope="col">Adresse</th>
            <th scope="col">PLZ</th>
            <th scope="col">Ort</th>
        </tr>
    </thead>
    <tbody>
        {#each craftsmans as craftsman}
            <tr>
                <td>{craftsman.companyName}</td>
                <td>{craftsman.street}</td>
                <td>{craftsman.plz}</td>
                <td>{craftsman.city}</td>
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
                    href={"#/craftsmansoverview?page=" + (i + 1)}>{i + 1}</a
                >
            </li>
        {/each}
    </ul>
</nav>
