<script>
	import Router from "svelte-spa-router";
	import routes from "./routes";
	import { isAuthenticated, user } from "./store";
	import auth from "./auth.service";
</script>

<div id="app">
	<nav class="navbar navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#/">Crafty</a>
			<button
				class="navbar-toggler"
				type="button"
				data-bs-toggle="collapse"
				data-bs-target="#navbarNav"
				aria-controls="navbarNav"
				aria-expanded="false"
				aria-label="Toggle navigation"
			>
				<span class="navbar-toggler-icon" />
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					{#if $isAuthenticated }
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#customers" role="button" data-bs-toggle="dropdown" aria-expanded="false">
							  Kunden
							</a>
							<ul class="dropdown-menu">
							  <li><a class="dropdown-item" href="#/customers">Neuen Kunden erfassen</a></li>
							  <li><a class="dropdown-item" href="#/customersoverview">Übersicht aller Kunden</a></li>
							</ul>
						  </li>
						
					{/if}
					{#if $isAuthenticated}
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#projects" role="button" data-bs-toggle="dropdown" aria-expanded="false">
						  Projekte
						</a>
						<ul class="dropdown-menu">
						  <li><a class="dropdown-item" href="#/projects">Neuen Handwerker erfassen</a></li>
						  <li><a class="dropdown-item" href="#/projectsoverview">Übersicht aller Handwerker</a></li>
						</ul>
					  </li>
					  <li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#craftsmans" role="button" data-bs-toggle="dropdown" aria-expanded="false">
						  Handwerker
						</a>
						<ul class="dropdown-menu">
						  <li><a class="dropdown-item" href="#/craftsmans">Neues Projekt erfassen</a></li>
						  <li><a class="dropdown-item" href="#/craftsmansoverview">Übersicht aller Projekte</a></li>
						</ul>
					  </li>
					{/if}
					
						<li class="nav-item">
							<a class="nav-link" href="#/craftsmansoverview">Handwerkerübersicht</a>
						</li>
					
					
						<li class="nav-item">
							<a class="nav-link" href="#/projectsoverview">Projektübersicht</a>
						</li>
						{#if $isAuthenticated}
						<li class="nav-item">
							<a class="nav-link" href="#/account">Account</a>
						</li>
					{/if}
				</ul>
				<div class="d-flex">
					{#if $isAuthenticated}
						<span class="navbar-text me-2">
							{$user.name}
						</span>
						<button
							type="button"
							class="btn btn-warning"
							on:click={auth.logout}>Log Out</button
						>
					{:else}
						<button
							type="button"
							class="btn btn-warning"
							on:click={auth.loginWithPopup}>Log In</button
						>
					{/if}
					
				</div>
			</div>
		</div>
	</nav>

	<div class="container mt-3">
		<Router {routes} />
	</div>
</div>
