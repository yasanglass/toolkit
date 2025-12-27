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
  :compose["compose"]
  :koin["koin"]
  subgraph :sample
    :sample:composeApp["composeApp"]
  end

  :compose --> :core
  :koin --> :compose
  :koin --> :core
  :sample:composeApp --> :koin

classDef kotlin-multiplatform fill:#C792EA,stroke:#fff,stroke-width:2px,color:#fff;
classDef android-application fill:#2C4162,stroke:#fff,stroke-width:2px,color:#fff;
class :compose kotlin-multiplatform
class :core kotlin-multiplatform
class :koin kotlin-multiplatform
class :sample:composeApp android-application

```