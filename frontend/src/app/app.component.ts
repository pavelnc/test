import {Component} from '@angular/core';
import {NodeService} from './node.service';
import {NodeDt} from './types';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';
  nodes: NodeDt[];
  nodeForm: FormGroup;
  changeForm: FormGroup;


  constructor(private formBuilder: FormBuilder,
              private nodeService: NodeService) {
    this.nodeForm = this.formBuilder.group({
      id: [''],
    });
    this.changeForm = this.formBuilder.group({
      id: ['', Validators.required],
      parent: ['', Validators.required],
    });
  }


  getNode(): void {
    this.nodeService.getNodes(this.nodeForm.value.id).subscribe(value => this.nodes = value);
  }

  changeNode(): void {
    if (this.changeForm.invalid) {
      return;
    }
    this.nodeService.updateNodes({
      description: '',
      lvl: 0,
      parent: this.changeForm.value.parent,
      path: '',
      id: this.changeForm.value.id
    }).subscribe();
  }
}
