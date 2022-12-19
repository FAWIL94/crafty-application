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


    
    function createProject() {
        var config = {
            method: "post",
            url: api_root + "/api/project",
            headers: {
                "Content-Type": "application/json",
            },
            data: project,
        };

        axios(config)
            .then(function (response) {
                alert("Project created");
                
            })
            .catch(function (error) {
                alert("Could not create Project");
                console.log(error);
            });
    }


    
</script>

<h1 class="mt-3">Erfasse ein neues Projekt</h1>
<form class="mb-5">
    <div class="row mb-3">
        <div class="col">
            <label class="form-label" for="name">Projektname</label>
            <input
                bind:value={project.name}
                class="form-control"
                id="name"
                type="text"
            />
        </div>
    </div>
    <div class="row mb-3">
        <div class="col">
            <label class="form-label" for="name">Beschreibe dein Projekt</label>
            <input
                bind:value={project.description}
                class="form-control"
                id="name"
                type="text"
            />
        </div>
    </div>
    <div class="row mb-3">
        <div class="col">
            <label class="form-label" for="type">Type</label>
            <select
                bind:value={project.projectType}
                class="form-select"
                id="type"
                type="text"
            >
                <option value="BUILD">Bau</option>
                <option value="RENOVATE">Renovation</option>
                <option value="REPAIR">Reparatur</option>
                <option value="OTHER">Andere</option>
            </select>
        </div>
        <div class="col">
            <label class="form-label" for="costs">Preisvorstellung</label>
            <input
                bind:value={project.costs}
                class="form-control"
                id="costs"
                type="number"
            />
        </div>
    </div>
    <button type="button" class="btn btn-warning" on:click={createProject}
        >Projekt erfassen</button
    >
</form>

