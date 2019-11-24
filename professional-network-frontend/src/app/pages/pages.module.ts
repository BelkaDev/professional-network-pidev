import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PagesComponent } from './pages/pages.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule, MatButtonModule, MatSidenavModule, MatIconModule, MatListModule, MatFormFieldModule, MatSelectModule, MatOptionModule, MatInputModule } from '@angular/material';

@NgModule({
  declarations: [PagesComponent],
  imports: [
    CommonModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatFormFieldModule,
    MatSelectModule,
    MatOptionModule,
    MatInputModule
    
  ],
  exports: [PagesComponent]
})
export class PagesModule { }
