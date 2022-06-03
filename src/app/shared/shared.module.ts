import { CommonModule } from '@angular/common';
import { ModuleWithProviders, NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { ButtonComponent } from './components/button/button.component';
import { CustomTableComponent } from './components/custom-table/custom-table.component';
import { TasksService } from './services/tasks.service';
import { UserService } from './services/user.service';


@NgModule({
  declarations: [
    ButtonComponent,
    CustomTableComponent
  ],
  imports: [
    CommonModule,
    MatIconModule,
    MatButtonModule
  ],
  exports: [
    ButtonComponent,
    CustomTableComponent
  ]
})
export class SharedModule {
  static forRoot(): ModuleWithProviders<SharedModule> {
    return {
      ngModule: SharedModule,
      providers: [UserService, TasksService]
    }
  }
}
