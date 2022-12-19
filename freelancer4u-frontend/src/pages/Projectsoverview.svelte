<script>
    import axios from "axios";

    import { querystring } from "svelte-spa-router";

    const api_root = "http://localhost:8080";

    let currentPage;
    let earningsMin, earningsMax;
    let nrOfPages = 0;

    let projects = [];
    let project = {
        name: null,
        description: null,
        costs: null,
        projectType: null,
        projectState: null,
    };

    $: {
        let searchParams = new URLSearchParams($querystring);

        if (searchParams.has("page")) {
            currentPage = searchParams.get("page");
        } else {
            currentPage = "1";
        }

        getProjects();
    }

    function getProjects() {
        let query = "pageSize=4&page=" + currentPage;

        if (earningsMin) {
            query += "&min=" + earningsMin;
        }
        if (earningsMax) {
            query += "&max=" + earningsMax;
        }

        var config = {
            method: "get",
            url: api_root + "/api/project?" + query,
            headers: {},
        };

        axios(config)
            .then(function (response) {
                projects = response.data.content;
                nrOfPages = response.data.totalPages;
            })
            .catch(function (error) {
                alert("Could not get projects");
                console.log(error);
            });
    }

    
</script>


<h1>Übersicht aller bisher erfassten Projekte</h1>

<div class="row my-3">
    <div class="col-auto">
        <label for="" class="col-form-label">Kosten: </label>
    </div>
    <div class="col-3">
        <input
            class="form-control"
            type="number"
            placeholder="from"
            bind:value={earningsMin}
        />
    </div>
    <div class="col-3">
        <input
            class="form-control"
            type="number"
            placeholder="to"
            bind:value={earningsMax}
        />
    </div>
    <div class="col-3">
        <button class="btn btn-warning" on:click={getProjects}>Suchen</button>
    </div>
</div>

<table class="table">
    <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Projektbeschreibung</th>
            <th scope="col">Kosten</th>
            <th scope="col">Projekttyp</th>
            <th scope="col">Projektstatus</th>
            
        </tr>
    </thead>
    <tbody>
        {#each projects as project}
            <tr>
                <td>{project.name}</td>
                <td>{project.description}</td>
                <td>{project.costs}</td>
                <td>{project.projectType}</td>
                <td>{project.projectState}</td>
                
            </tr>
        {/each}
    </tbody>
</table>
<nav>
    <ul class="pagination">
        {#each Array(nrOfPages) as _, i}
            <li class="page-item warning">
                <a
                    class="page-link"
                    class:active={currentPage == i + 1}
                    href={"#/projectsoverview?page=" + (i + 1)}>{i + 1}</a
                >
            </li>
        {/each}
    </ul>
</nav>
