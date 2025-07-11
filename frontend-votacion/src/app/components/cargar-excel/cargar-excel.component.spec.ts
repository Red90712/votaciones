import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CargarExcelComponent } from './cargar-excel.component';

describe('CargarExcelComponent', () => {
  let component: CargarExcelComponent;
  let fixture: ComponentFixture<CargarExcelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CargarExcelComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CargarExcelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
