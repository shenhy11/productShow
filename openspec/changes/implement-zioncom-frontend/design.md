## Context

Zioncom's website requires a production-ready Nuxt 3 frontend. The initial skeleton and basic routing currently exist, but we must implement high-fidelity UI components, robust product categorization filtering, and deeply interactive support service forms (e.g. 3-tier cascade select for models and firmware). We will use existing crawled assets (`crawl_output` dir) to achieve UI/UX matching `www.zioncom.net`.

## Goals / Non-Goals

**Goals:**
- Implement global and local Nuxt 3 UI components conforming to a premium Glassmorphism aesthetic.
- Construct data structures mapped to the real 14-category product hierarchy.
- Build interactive frontend components (Product Cascade, Debounce Search, Support Form) using proper Composition API patterns.
- Mock all API endpoints reliably prior to backend linkage.

**Non-Goals:**
- Connecting directly to live external REST APIs (data will be mocked via static JSON or composable stubs in this phase).
- Writing the Java Spring Boot Backend logic.

## Decisions

- **Styling**: We will utilize Nuxt's native Vue component scoped styles (Vanilla CSS / SCSS if configured) with CSS Variables (Design Tokens) to systematically support the Glassmorphism, colors, and responsive mixins without introducing massive unvetted frameworks, adhering to exact project rules. 
- **Mock Data Loading**: `useAsyncData` combined with our dynamic `useHttp.ts` will fetch predefined `json` structures (e.g., from `server/api/mock/..` or `public/data/..`) to emulate SSR caching cleanly.
- **Cascade Form Logic**: The download center selection uses Vue 3 `watch` bindings combined with `lodash-es` or native `debounce` to fetch firmwares without spamming fetches.

## Risks / Trade-offs

- [Risk] Reusing pure CSS might lead to bloated single-file components. → Mitigation: Establish a `assets/css/main.css` core token file for variables, spacing, shadows, and base animations. 
- [Risk] SSR hydration mismatches when mapping deeply nested object trees (like the 14-tier products). → Mitigation: Clearly define TypeScript interfaces for the product models and ensure mock data is identical between server/client context.
