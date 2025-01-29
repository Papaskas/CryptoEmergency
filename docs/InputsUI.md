# Архитектура логики и наследования текстовых полей

```mermaid
graph TB
    Input([Input]) --> Validator[VaidatorInput]
    Input --> OTP[OneTimePassword]
    Input --> Date[DateInput]
    Input --> Selector[SelectorInput]
    Validator --> Mask[MaskInput] --> Phone[PhoneInput]
    Validator --> Password[PasswordInput] --> DblPass[DoublePasswordInput]
    Validator --> MultiLine{MultiLineInput}
    MultiLine --> MulitLine1["С внешним видом с валидацией на количество символов"]
    MultiLine --> MulitLine2["Без дополнительной логики"]
    Validator --> Email[EmailInput]
```

`InputSamples` - Примеры использования текстовых полей
---
`Input` - Базовое текстовое поле которое определяет только внешний вид текстового поля.<br/>
`ValidatorInput` - Наследник `Input` с логикой валидации.<br/>
`OneTimePassword` - Наследник `Input` с логикой одноразового пароля.<br/>
`DateInput` - Наследник `Input` с логикой даты.<br/>
`SelectorInput` - Наследник `Input` с логикой селектора элементов.<br/>
`MaskInput` - Наследник `ValidatorInput` с логикой маски ввода.<br/>
`PhoneInput` - Наследник `MaskInput` логикой ввода номера телефона с помощью маски.<br/>
`PasswordInput` - Наследник `ValidatorInput` с логикой ввода пароля.<br/>
`DoublePasswordInput` - Группа компонентов `PasswordInput` с логикой двойного пароля.<br/>
`EmailInput` - Наследник `ValidatorInput` с логикой ввода email.<br/>
`MultiLineInput` - Наследник `ValidatorInput` с логикой многостраничного ввода с паттерном минимальных и максимальных символов.<br/>
`MultiLineInput` - Наследник `ValidatorInput` с логикой многостраничного ввода без валидатора количества символов.<br/>