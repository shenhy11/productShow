## ADDED Requirements

### Requirement: Firmware Cascade Selector
The download center SHALL implement a three-tier cascade (Category -> Subcategory -> Model) utilizing debounce mechanisms for API requests.

#### Scenario: Searching for specific firmware
- **WHEN** user selects 'Router' and then 'X6100UA'
- **THEN** associated firmware options execute via fetch with caching and display in results table

### Requirement: Email Tech Form Validation
The support form SHALL validate required fields (subject, email, message, system) before allowing submission logic to execute.

#### Scenario: Submitting empty form
- **WHEN** user clicks "Send" with empty inputs
- **THEN** the system prevents submission and highlights error fields in red
