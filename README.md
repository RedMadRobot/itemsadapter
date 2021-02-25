# ItemsAdapter <GitHub path="RedMadRobot/itemsadapter"/>
[![Version](https://img.shields.io/maven-central/v/com.redmadrobot.itemsadapter/itemsadapter?style=flat-square)][mavenCentral] [![Build Status](https://img.shields.io/github/workflow/status/RedMadRobot/itemsadapter/CI/main?style=flat-square)][ci] [![License](https://img.shields.io/github/license/RedMadRobot/itemsadapter?style=flat-square)][license]

The simple adapter to render static data in `RecyclerView`.

---
<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [Installation](#installation)
- [Rationale](#rationale)
    - [When should I not use `ItemsAdapter`?](#when-should-i-not-use-itemsadapter)
- [Usage](#usage)
  - [`ViewBinding` support](#viewbinding-support)
  - [Features](#features)
    - [Context](#context)
    - [Looping mode](#looping-mode)
- [Contributing](#contributing)
- [License](#license)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## Installation

Add the dependency:
```groovy
repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation("com.redmadrobot.itemsadapter:itemsadapter:1.1")
    // or if you use viewbinding
    implementation("com.redmadrobot.itemsadapter:itemsadapter-viewbinding:1.1")
}
```

## Rationale

There are cases when you just need to render a simple list of static data without the hassle.
You don't want to use complex solutions like [Epoxy] or [Groupie] for this purpose.
This task is `ItemsAdapter` was created for.
`ItemsAdapter` offers you simple DSL to create adapters.

#### When should I not use `ItemsAdapter`?

- **When** you need to update and re-draw data in adapter
- **When** you need to render elements with complex logic

## Usage

The simplest case of usage is:
```kotlin
recyclerView.adapter = itemsAdapter(regions) { region ->
    bind(R.layout.view_region) { // this: View
        view_region_title.text = region.title
        view_region_description.text = region.description
    }
}
```

If you have more than one view type, you can use operator `when`:
```kotlin
recyclerView.adapter = itemsAdapter(contactsItems) { item ->
    when (item) {
        is ContactsItem.Header -> bind(R.layout.view_contacts_header) {
            view_contacts_header_letter.text = item.letter
        }

        is ContactsItem.Entry -> bind(R.layout.view_contacts_entry) {
            view_contacts_entry_name.text = item.name
            view_contacts_entry_phone.text = item.phone
        }
    }
}
```

> :exclamation: Note that kotlin synthetics are deprecated since Kotlin 1.4.20, so it is better to use ViewBinding or `findViewById`.

### `ViewBinding` support

If you use `ViewBinding`, use `itemsadapter-viewbinding` in place of `itemsadapter`.  
Then you can use method `bind` with `ViewBinding`:
```kotlin
recyclerView.adapter = itemsAdapter(regions) { region ->
    bind<ViewRegionBinding>(R.layout.view_region) { // this: ViewRegionBinding
        title.text = region.title
        description.text = region.description
    }
}
```

### Features

#### Context

Within `itemsAdapter` block you can use contextual data:

| Field        | Description               |
|--------------|---------------------------|
| `index: Int` | Index of the current item |

#### Looping mode

Set parameter `isLooping` to `true` to use `ItemsAdapter` in looping mode.
It will only work if you have more than one element in data list.

## Contributing
Merge requests are welcome.
For major changes, please open an issue first to discuss what you would like to change.

## License

[MIT][license]

[mavenCentral]: https://search.maven.org/search?q=g:com.redmadrobot.itemsadapter
[ci]: https://github.com/RedMadRobot/itemsadapter/actions
[license]: LICENSE

[epoxy]: https://github.com/airbnb/epoxy/
[groupie]: https://github.com/lisawray/groupie/
