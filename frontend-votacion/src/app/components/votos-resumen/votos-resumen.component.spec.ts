import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VotosResumenComponent } from './votos-resumen.component';

describe('VotosResumenComponent', () => {
  let component: VotosResumenComponent;
  let fixture: ComponentFixture<VotosResumenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VotosResumenComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VotosResumenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
