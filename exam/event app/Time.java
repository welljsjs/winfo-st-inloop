// Copyright 2022 Julius Schmidt
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

public class Time {
  private int hour;
  private int minute;

  public Time(int hour, int minute) {
    if (hour < 0 || hour > 23 || minute < 0 || minute > 59)
      throw new IllegalArgumentException(
          "hour and minute must be non-negative and less than or equal to 23 or 59, respectively.");

    this.hour = hour;
    this.minute = minute;
  }

  public int getHour() {
    return hour;
  }

  public int getMinute() {
    return minute;
  }
}