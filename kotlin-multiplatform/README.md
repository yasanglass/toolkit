### Modules

```mermaid
%%{
  init: {
    'theme': 'neutral'
  }
}%%

graph LR
  :about["about"]
  :compose["compose"]
  :compose["compose"]
  :core["core"]
  :koin["koin"]
  :about["about"]
  :koin["koin"]
  subgraph :sample
    :sample:composeApp["composeApp"]
  end

  :about --> :compose
  :compose --> :core
  :koin --> :about
  :sample:composeApp --> :koin

classDef kotlin-multiplatform fill:#C792EA,stroke:#fff,stroke-width:2px,color:#fff;
classDef android-application fill:#2C4162,stroke:#fff,stroke-width:2px,color:#fff;
class :about kotlin-multiplatform
class :compose kotlin-multiplatform
class :core kotlin-multiplatform
class :koin kotlin-multiplatform
class :sample:composeApp android-application

```