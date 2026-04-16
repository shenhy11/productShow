## 1. Setup & Styling Token Foundation

- [x] 1.1 Create `assets/css/main.css` implementing brand colors (red `#E60012`/black) and Glassmorphism CSS variables (blur drops, borders).
- [x] 1.2 Import `main.css` globally in `nuxt.config.ts` or `app.vue`.
- [x] 1.3 Create reusable global container/row utility classes mapped from Tailwind concepts to vanilla CSS variables.

## 2. Core UI Components

- [x] 2.1 Develop `ZProductCard.vue` to display main product image, title, sub-specs, and include hover scaling/box-shadow variables.
- [x] 2.2 Develop `ZBreadcrumb.vue` utilizing `vue-router`'s route matching to generate dynamic navigable links.
- [x] 2.3 Develop `ZDataGrid.vue` for clean table-styled FAQ data rendering.
- [ ] 2.4 Test ZProductCard responsiveness across mobile/tablet formats.

## 3. Product Display & Catalog

- [ ] 3.1 Extract the 14-tier product categorizations from `crawl_output/crawl_results.json` and dump to `public/mock/categories.json`.
- [ ] 3.2 Implement `pages/products/index.vue` to fetch the category structure via `useAsyncData` + `mock/categories.json`.
- [ ] 3.3 Create a dynamic Sidebar / Top-tab filter in `products/index.vue` to filter displayed `ZProductCard`s based on category.

## 4. Support Services & Advanced Interactions

- [ ] 4.1 Develop `ZCascadeSelector.vue` taking nested tree data for the 3-tier inputs.
- [ ] 4.2 Implement `pages/support/download.vue` with `lodash-es` debounce calling `useHttp` API (or mock JSON) when third tier (Model) is selected.
- [ ] 4.3 Update `pages/support/email-support.vue` with strict Javascript form validations ensuring no empty subject, email, or message.
