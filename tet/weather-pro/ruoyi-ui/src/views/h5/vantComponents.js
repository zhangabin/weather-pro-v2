import {
  Cell,
  CellGroup,
  Form,
  Field,
  Button,
  Picker,
  Popup ,
  NavBar ,
  Checkbox,
  CheckboxGroup,
  RadioGroup,
  Radio,
  DatetimePicker,
  Icon
} from 'vant';

/**
 *
 * @returns vant 组件
 */
export function vantComponents(){
  return  {
    [Cell.name]:Cell,
    [CellGroup.name]:CellGroup,
    [Form.name]:Form,
    [Field.name]:Field,
    [Button.name]:Button,
    [Picker.name]:Picker,
    [Popup.name]:Popup,
    [NavBar.name]:NavBar,
    [Checkbox.name]:Checkbox,
    [CheckboxGroup.name]:CheckboxGroup,
    [RadioGroup.name]:RadioGroup,
    [Radio.name]:Radio,
    [DatetimePicker.name]:DatetimePicker,
    [Icon.name]:Icon
  }
}
