### Modules

```mermaid
%%{
  init: {
    'theme': 'neutral'
  }
}%%

graph LR
  :compose["compose"]
  :core["core"]
  :koin["koin"]

  :compose --> :core
  :koin --> :core

classDef kotlin-multiplatform fill:#C792EA,stroke:#fff,stroke-width:2px,color:#fff;
class :compose kotlin-multiplatform
class :core kotlin-multiplatform
class :koin kotlin-multiplatform

```