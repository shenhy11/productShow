## ADDED Requirements

### Requirement: Multi-category Display Page
The system SHALL render the Products index page displaying 14+ hierarchical categories filtering via sidebar or top tabs.

#### Scenario: Switching categories on the list
- **WHEN** user selects `Wireless USB Adapter` category
- **THEN** product grid updates to show only products associated with that category with SSR capability

### Requirement: Detailed Product View
The system SHALL display high-quality images, main specifications, and a link to associated firmware on the individual product detail page.

#### Scenario: Accessing product details
- **WHEN** navigation goes to `/products/[id]`
- **THEN** UI fetches and populates the details layout seamlessly
