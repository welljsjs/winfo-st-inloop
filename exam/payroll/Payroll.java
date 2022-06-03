import java.util.Objects;

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

public class Payroll {
  private PayrollDisposition disposition;
  private int payday;

  public Payroll(PayrollDisposition disposition, int payday) {
    Objects.requireNonNull(disposition, "disposition must be non-null");
    if (payday < 1 || payday > 30)
      throw new IllegalArgumentException("Expected payday to be between 1 and 30 (inclusive)");

    this.disposition = disposition;
    this.payday = payday;
  }

  public void doPayroll(PayrollDB db) {
    Objects.requireNonNull(db, "db must be non-null");

    for (Employee empl : db.getEmployeeList()) {
      if (empl instanceof Volunteer)
        continue;
      if (empl.isPayday(payday))
        disposition.sendPayment(empl, ((Appointee) empl).calculatePay() - empl.calculateDeductions());
    }
  }
}