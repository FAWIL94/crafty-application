
import Home from "./pages/Home.svelte";
import Customers from "./pages/Customers.svelte";
import Projects from "./pages/Projects.svelte";
import Craftsmans from "./pages/Craftsmans.svelte";
import Projectsoverview from "./pages/Projectsoverview.svelte"
import Craftsmansoverview from "./pages/Craftsmansoverview.svelte"
import Customersoverview from "./pages/Customersoverview.svelte"
import Account  from "./pages/Account.svelte";
export default {
    '/': Home,
    '/home': Home,
    '/customers': Customers,
    '/craftsmans': Craftsmans,
    '/projects': Projects,
    '/projectsoverview': Projectsoverview,
    '/craftsmansoverview': Craftsmansoverview,
    '/customersoverview' : Customersoverview,
    '/account' : Account,
    
}