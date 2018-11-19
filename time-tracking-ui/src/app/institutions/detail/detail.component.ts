import { Component, OnInit, Input } from '@angular/core';
import { Institution } from '../../model/institution';
import { InstitutionService } from '../../service/institution.service';
import { MessageService } from '../../service/message.service';
import { InstitutionsComponent } from '../institutions.component';


@Component({
  selector: 'app-instititution-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
  @Input() institution: Institution;

  constructor(
    private messageService: MessageService,
    private institutionService: InstitutionService,
    private institutionsComponent: InstitutionsComponent ) { }

  ngOnInit() {
  }

  update(institution: Institution): void {
    if (institution.id != null) {
      this.institutionService.update(institution).subscribe(() => this.log('Updated Successfully'));
    } else {
      this.add(institution);
    }
  }

  add(institution: Institution): void {
    this.institutionService.add(institution).subscribe(() => {
      this.log('Added Successfully');
      this.institutionsComponent.getAllInstitutions();
    });
  }

  private log(message: string): void {
    this.messageService.add(`Institution ${message}`);
  }
}
