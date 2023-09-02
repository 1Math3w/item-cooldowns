# ItemCooldowns

Simple plugin that will add possibility to set up cooldowns for items

## Features

- Can add as many items as you want
- Customizable cooldown for each item
- Customizable cooldown message for each item
- Customizable ready message for each item
- HEX colors

## Configurations

**items.yml**

```yml
ender_pearl:
  cooldown: 10 # In seconds
  cooldown-message: '&7You have to wait &c%cooldown%s &7to use ender pearl again'
  ready-message: '&aYou can use ender pearl again'
```

If you want to add cooldown to another item copy & paste this configuration section. Name of the section needs to be
lower cased material name 