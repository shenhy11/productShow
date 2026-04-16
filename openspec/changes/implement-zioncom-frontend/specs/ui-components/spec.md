## ADDED Requirements

### Requirement: Design Token Setup
The system SHALL provide a central CSS file defining CSS variables for brand colors (red/black), font sizes, and Glassmorphism shadow/blur drops to standardize UI matching zioncom.net.

#### Scenario: Using tokens on a component
- **WHEN** a `.card` class applies `var(--color-primary)`
- **THEN** it renders with the exact brand red defined in the token system

### Requirement: Product Card Component
The generic `ZProductCard` component SHALL display an image, title, subtitle, and apply hover animations reflecting premium styling.

#### Scenario: Hovering product card
- **WHEN** user hovers over a product card
- **THEN** a slight scale-up transform and box-shadow enhancement occur

### Requirement: Breadcrumb Component
A dynamic `ZBreadcrumb` component SHALL be provided to navigate deep nested routes.

#### Scenario: Viewing a deep product details page
- **WHEN** user loads a product page like `products/wireless/x6100ua`
- **THEN** breadcrumb reflects `Home > Products > Wireless Router > X6100UA`
