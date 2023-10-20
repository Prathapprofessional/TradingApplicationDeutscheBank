# How to create Signal JSON

This README provides an overview of the JSON structure used to represent signal file. For each signal a seperate
json file needs to be created, each signal associated with one or more actions.

## JSON File Structure

The JSON file is structured as follows:

```json

{
  "SignalId": 1,
  "Actions": [
    {
      "ActionType": "SETUP"
    },
    {
      "ActionType": "SET_ALGO_PARAM",
      "Param": 1,
      "Value": 60
    },
    {
      "ActionType": "PERFORM_CALC"
    },
    {
      "ActionType": "SUBMIT_TO_MARKET"
    },
    {
      "ActionType": "REVERSE",
      "_comment": "Optional comment to add any additional information"
    }
  ]
}
```

<span style="color:yellow"><b>SignalId</b></span> - is a unique identifier for each signal.

<span style="color:yellow"><b>Actions</b></span> - is a list of actions performed for the signal. Each action is
defined by <span  style="color:yellow">ActionType</span> and its corresponding parameters.


Possible values for "ActionType" are listed in the following table:

### ActionType - Allowed Values

| ActionType       | Params information and its type             |
|------------------|---------------------------------------------|
| SETUP            | No params required                          |
| SET_ALGO_PARAM   | 1. Param: Type Integer 2.Value Type Integer |
| PERFORM_CALC     | No params required                          |
| SUBMIT_TO_MARKET | No params required                          |
| REVERSE          | No params required                          |

<span style="color:yellow"><b>_comment</b></span> field is optional and can be used to add any additional information.



