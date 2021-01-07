import React from 'react';
import {Button} from 'react-native';
import CalendarModule from '../../modules/CalendarModule';

const NewModuleButton = () => {
  const onPress = () => {
    /*CalendarModule.createCalendarEvent(
      'testName',
      'TestLocation',
      (eventId) => {
        console.log(`Created a new event with id ${eventId}`);
      },
    );*/
    CalendarModule.Hello();
  };

  return <Button title="Click" color="#841584" onPress={onPress} />;
};

export default NewModuleButton;
