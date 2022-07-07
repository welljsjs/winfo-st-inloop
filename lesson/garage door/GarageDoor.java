// Copyright 2022 Julius Schmidt
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//     http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

public class GarageDoor {
  private DoorState currentState;
  private Motor myMotor = new Motor();
  private final DoorState closedState = new Closed();
  private final DoorState openingState = new Opening();
  private final DoorState openState = new Open();
  private final DoorState closingState = new Closing();

  public GarageDoor() {
    super();
    currentState = closedState;
  }

  public void openDoor() {
    currentState.openDoor();
  }

  public void stopper() {
    currentState.stopper();
  }

  public void closeDoor() {
    currentState.closeDoor();
  }

  private abstract class DoorState {
    public void openDoor() {
      throw new IllegalStateException();
    }

    public void closeDoor() {
      throw new IllegalStateException();
    }

    public void stopper() {
      throw new IllegalStateException();
    }
  }

  private class Closed extends DoorState {
    @Override
    public void openDoor() {
      myMotor.upwards();
      currentState = openingState;
    }
  }

  private class Opening extends DoorState {
    @Override
    public void closeDoor() {
      myMotor.downwards();
      currentState = closingState;
    }

    @Override
    public void stopper() {
      myMotor.stop();
      currentState = openState;
    }
  }

  private class Open extends DoorState {
    @Override
    public void closeDoor() {
      myMotor.downwards();
      currentState = closingState;
    }
  }

  private class Closing extends DoorState {
    @Override
    public void openDoor() {
      myMotor.upwards();
      currentState = openingState;
    }

    @Override
    public void stopper() {
      myMotor.stop();
      currentState = closedState;
    }
  }

}
