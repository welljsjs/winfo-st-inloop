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

public class Volunteer extends Employee {
  public Volunteer(String id) {
    super(id);
  }

  @Override
  public boolean isPayday(int dayOfMonth) {
    if (dayOfMonth < 1 || dayOfMonth > 30)
      throw new IllegalArgumentException("Expected dayOfMonth to be between 1 and 30 (inclusive)");
    return false;
  }

  @Override
  public double calculatePay() throws UnpayableEmployeeException {
    throw new UnpayableEmployeeException("Volunteers are not payable");
  }

  @Override
  public double calculateDeductions() {
    return 0;
  }
}
