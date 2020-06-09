import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ThirdpartyMerchantComponent } from './thirdparty-merchant.component';

describe('ThirdpartyMerchantComponent', () => {
  let component: ThirdpartyMerchantComponent;
  let fixture: ComponentFixture<ThirdpartyMerchantComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ThirdpartyMerchantComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ThirdpartyMerchantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
