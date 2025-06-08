import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GraficaVotosComponent } from './grafica-votos.component';

describe('GraficaVotosComponent', () => {
  let component: GraficaVotosComponent;
  let fixture: ComponentFixture<GraficaVotosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GraficaVotosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GraficaVotosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
