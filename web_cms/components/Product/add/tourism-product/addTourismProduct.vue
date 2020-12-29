<template>
  <div class="add-tourism-prod">
    <v-row v-if="hydrated">
      <v-col cols="12" class="d-flex sticky py-0" style="top: 75px; z-index: 2">
        <v-spacer></v-spacer>
        <v-btn
          class="text-none white--text mr-3"
          color="#4873C2"
          dark
          depressed
          width="120px"
          @click="confirmResetDialogisOpened = true"
        >
          <img class="mr-1" src="~/assets/icon/change.png" />
          Đặt lại
        </v-btn>
        <v-btn
          class="text-none white--text mr-3"
          color="#4873C2"
          dark
          depressed
          width="120px"
        >
          <img class="mr-1" src="~/assets/icon/view.svg" />
          Xem trước
          <template v-slot:loader>
            <span><span>Xin chờ...</span></span>
          </template>
        </v-btn>
      </v-col>

      <v-col cols="12">
        <p class="fs-14 font-weight-bold d-flex align-center">
          SKU
          <span class="error--text ml-1"> * </span>

          <!-- Switch -->
          <span class="d-inline-block ml-6">
            <v-chip small outlined>{{ sku }}</v-chip>
          </span>
        </p>
      </v-col>
      <!-- System fields -->
      <v-col
        v-for="(sysField, sysFieldIdx) in systemFields"
        :key="`field-${sysField.name}-${sysFieldIdx}`"
        cols="12"
      >
        <p
          class="fs-14 font-weight-bold"
          :class="
            sysField.attributeTypeId ===
              $store.state.productAttrs.attrTypes.switch &&
            'd-flex align-center'
          "
        >
          {{ sysField.name }}
          <span v-if="sysField.required === 1" class="error--text ml-1">
            *
          </span>

          <!-- Switch -->
          <span class="d-inline-block ml-6">
            <v-switch
              v-if="
                sysField.attributeTypeId ===
                $store.state.productAttrs.attrTypes.switch
              "
              v-model="sysField.value"
              class="ma-0"
              color="#47bbff"
              :true-value="1"
              :false-value="0"
              inset
              hide-details
            />
          </span>
        </p>

        <!-- Đoạn văn bản ngắn -->
        <template
          v-if="
            sysField.attributeTypeId ===
            $store.state.productAttrs.attrTypes.textField
          "
        >
          <v-tabs v-model="sysField.tab" class="transparent-tabs">
            <template v-for="(tab, tabIdx) in sysField.attributeLangList">
              <v-tab
                :key="`${sysField.name}-${sysFieldIdx}-tab-${tabIdx}`"
                :class="sysField.tab !== tabIdx && 'black--text'"
                active-class="primary--text"
                class="text-none font-weight-medium letter-spacing-normal fs-14"
                >{{ tab.name }}</v-tab
              >

              <v-tab-item
                :key="`${sysField.name}-${sysFieldIdx}-tab-item-${tabIdx}`"
              >
                <v-text-field
                  v-model="tab.value"
                  :error-messages="tab.errors"
                  :placeholder="sysField.name"
                  class="border-1 fs-14"
                  solo
                  flat
                  dense
                  @input="tab.errors = []"
                ></v-text-field>
              </v-tab-item>
            </template>
          </v-tabs>
        </template>

        <!-- Đoạn văn bản dài -->
        <template
          v-if="
            sysField.attributeTypeId ===
            $store.state.productAttrs.attrTypes.textArea
          "
        >
          <v-tabs v-model="sysField.tab" class="transparent-tabs">
            <template v-for="(tab, tabIdx) in sysField.attributeLangList">
              <v-tab
                :key="`${sysField.name}-${sysFieldIdx}-tab-${tabIdx}`"
                :class="sysField.tab !== tabIdx && 'black--text'"
                active-class="primary--text"
                class="text-none font-weight-medium letter-spacing-normal fs-14"
                >{{ tab.name }}</v-tab
              >

              <v-tab-item
                :key="`${sysField.name}-${sysFieldIdx}-tab-item-${tabIdx}`"
              >
                <vue-editor
                  id="vue-2-editor-fix-height-1"
                  v-model="tab.value"
                  use-custom-image-handler
                  @text-change="tab.errors = []"
                />
                <div
                  v-if="tab.errors && tab.errors.length"
                  class="v-messages theme--light error--text mt-1"
                  role="alert"
                >
                  <div class="v-messages__wrapper">
                    <div class="v-messages__message">Không được để trống</div>
                  </div>
                </div>
              </v-tab-item>
            </template>
          </v-tabs>
        </template>

        <!-- Datepicker -->
        <template
          v-if="
            sysField.attributeTypeId ===
            $store.state.productAttrs.attrTypes.date
          "
        >
          <v-tabs v-model="sysField.tab" class="transparent-tabs">
            <template v-for="(tab, tabIdx) in sysField.attributeLangList">
              <v-tab
                :key="`${sysField.name}-${sysFieldIdx}-tab-${tabIdx}`"
                :class="sysField.tab !== tabIdx && 'black--text'"
                active-class="primary--text"
                class="text-none font-weight-medium letter-spacing-normal fs-14"
                >{{ tab.name }}</v-tab
              >

              <v-tab-item
                :key="`${tab.name}-${sysFieldIdx}-tab-item-${tabIdx}`"
              >
                <v-dialog
                  ref="dialog"
                  v-model="tab.modelIsOpened"
                  persistent
                  width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      :value="
                        $formatDate(
                          tab.value,
                          'YYYY-MM-DD',
                          'ddd DD/MM/YYYY',
                          true
                        )
                      "
                      :error-messages="tab.errors"
                      :placeholder="sysField.name"
                      class="border-1 fs-14"
                      solo
                      flat
                      dense
                      readonly
                      @input="tab.errors = []"
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-date-picker v-model="tab.value" scrollable>
                    <v-spacer></v-spacer>
                    <v-btn
                      text
                      color="primary"
                      class="font-weight-bold"
                      @click="tab.modelIsOpened = false"
                    >
                      OK
                    </v-btn>
                  </v-date-picker>
                </v-dialog>
              </v-tab-item>
            </template>
          </v-tabs>
        </template>

        <!-- Datetime -->
        <template
          v-if="
            sysField.attributeTypeId ===
            $store.state.productAttrs.attrTypes.dateTime
          "
        >
          <v-tabs v-model="sysField.tab" class="transparent-tabs">
            <template v-for="(tab, tabIdx) in sysField.attributeLangList">
              <v-tab
                :key="`${sysField.name}-${sysFieldIdx}-tab-${tabIdx}`"
                :class="sysField.tab !== tabIdx && 'black--text'"
                active-class="primary--text"
                class="text-none font-weight-medium letter-spacing-normal fs-14"
                >{{ tab.name }}</v-tab
              >

              <v-tab-item
                :key="`${sysField.name}-${sysFieldIdx}-tab-item-${tabIdx}`"
              >
                <v-dialog
                  ref="dialog"
                  v-model="tab.modelIsOpened"
                  persistent
                  width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-row no-gutters>
                      <v-col cols="1">
                        <v-select
                          v-model="tab.hour"
                          :items="hours"
                          :error-messages="tab.hourErrors"
                          placeholder="Giờ"
                          class="border-1 fs-14"
                          solo
                          flat
                          dense
                          @input="tab.hourErrors = []"
                        ></v-select>
                      </v-col>
                      <v-col cols="1">
                        <v-select
                          v-model="tab.minute"
                          :items="minutes"
                          :error-messages="tab.minuteErrors"
                          placeholder="Phút"
                          class="border-1 fs-14"
                          solo
                          flat
                          dense
                          @input="tab.minuteErrors = []"
                        ></v-select>
                      </v-col>
                      <v-col cols="10">
                        <v-text-field
                          :value="
                            $formatDate(
                              tab.value,
                              'YYYY-MM-DD',
                              'ddd DD/MM/YYYY',
                              true
                            )
                          "
                          :error-messages="tab.errors"
                          placeholder="Ngày"
                          class="border-1 fs-14 rounded-r"
                          solo
                          flat
                          dense
                          readonly
                          @input="tab.errors = []"
                          v-on="on"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                  </template>
                  <v-date-picker v-model="tab.value" scrollable>
                    <v-spacer></v-spacer>
                    <v-btn
                      text
                      color="primary"
                      class="font-weight-bold"
                      @click="tab.modelIsOpened = false"
                    >
                      OK
                    </v-btn>
                  </v-date-picker>
                </v-dialog>
              </v-tab-item>
            </template>
          </v-tabs>
        </template>

        <!-- Select -->
        <template
          v-if="
            sysField.attributeTypeId ===
            $store.state.productAttrs.attrTypes.select
          "
        >
          <v-tabs v-model="sysField.tab" class="transparent-tabs">
            <template v-for="(tab, tabIdx) in sysField.attributeLangList">
              <v-tab
                :key="`${sysField.name}-${sysFieldIdx}-tab-${tabIdx}`"
                :class="sysField.tab !== tabIdx && 'black--text'"
                active-class="primary--text"
                class="text-none font-weight-medium letter-spacing-normal fs-14"
                >{{ tab.name }}</v-tab
              >

              <v-tab-item
                :key="`${sysField.name}-${sysFieldIdx}-tab-item-${tabIdx}`"
              >
                <v-select
                  v-model="tab.value"
                  :items="tab.optionValues"
                  item-text="option"
                  item-value="id"
                  :error-messages="tab.errors"
                  :placeholder="sysField.name"
                  class="border-1 fs-14"
                  solo
                  flat
                  dense
                  @input="tab.errors = []"
                >
                </v-select>
              </v-tab-item>
            </template>
          </v-tabs>
        </template>

        <!-- Multi Select -->
        <template
          v-if="
            sysField.attributeTypeId ===
            $store.state.productAttrs.attrTypes.multiSelect
          "
        >
          <v-tabs v-model="sysField.tab" class="transparent-tabs">
            <template v-for="(tab, tabIdx) in sysField.attributeLangList">
              <v-tab
                :key="`${sysField.name}-${sysFieldIdx}-tab-${tabIdx}`"
                :class="sysField.tab !== tabIdx && 'black--text'"
                active-class="primary--text"
                class="text-none font-weight-medium letter-spacing-normal fs-14"
                >{{ tab.name }}</v-tab
              >

              <v-tab-item
                :key="`${sysField.name}-${sysFieldIdx}-tab-item-${tabIdx}`"
              >
                <v-select
                  v-model="tab.value"
                  :items="tab.optionValues"
                  item-text="option"
                  item-value="id"
                  :error-messages="tab.errors"
                  :placeholder="sysField.name"
                  class="border-1 fs-14"
                  solo
                  flat
                  dense
                  multiple
                  @input="tab.errors = []"
                >
                </v-select>
              </v-tab-item>
            </template>
          </v-tabs>
        </template>

        <!-- Radio -->
        <template
          v-if="
            sysField.attributeTypeId ===
            $store.state.productAttrs.attrTypes.radio
          "
        >
          <v-tabs v-model="sysField.tab" class="transparent-tabs">
            <template v-for="(tab, tabIdx) in sysField.attributeLangList">
              <v-tab
                :key="`${sysField.name}-${sysFieldIdx}-tab-${tabIdx}`"
                :class="sysField.tab !== tabIdx && 'black--text'"
                active-class="primary--text"
                class="text-none font-weight-medium letter-spacing-normal fs-14"
                >{{ tab.name }}</v-tab
              >

              <v-tab-item
                :key="`${sysField.name}-${sysFieldIdx}-tab-item-${tabIdx}`"
              >
                <v-radio-group
                  v-model="tab.value"
                  :error-messages="tab.errors"
                  row
                >
                  <v-radio
                    v-for="(option, optionIdx) in tab.optionValues"
                    :key="`${sysField.name}-${option.id}-${option.option}-${optionIdx}`"
                    :label="option.option"
                    :value="option.id"
                  ></v-radio>
                </v-radio-group>
              </v-tab-item>
            </template>
          </v-tabs>
        </template>

        <!-- Checkbox -->
        <template
          v-if="
            sysField.attributeTypeId ===
            $store.state.productAttrs.attrTypes.checkbox
          "
        >
          <v-tabs v-model="sysField.tab" class="transparent-tabs">
            <template v-for="(tab, tabIdx) in sysField.attributeLangList">
              <v-tab
                :key="`${sysField.name}-${sysFieldIdx}-tab-${tabIdx}`"
                :class="sysField.tab !== tabIdx && 'black--text'"
                active-class="primary--text"
                class="text-none font-weight-medium letter-spacing-normal fs-14"
                >{{ tab.name }}</v-tab
              >

              <v-tab-item
                :key="`${sysField.name}-${sysFieldIdx}-tab-item-${tabIdx}`"
              >
                <v-radio-group
                  :value="tab.value"
                  :error-messages="tab.errors"
                  multiple
                  row
                >
                  <v-radio
                    v-for="(option, optionIdx) in tab.optionValues"
                    :key="`${sysField.name}-${option.id}-${option.option}-${optionIdx}`"
                    :label="option.option"
                    :value="option.id"
                    off-icon="$checkboxOff"
                    on-icon="$checkboxOn"
                    @click="toggleMultipleRadio(option.id, tab.value)"
                  ></v-radio>
                </v-radio-group>
              </v-tab-item>
            </template>
          </v-tabs>
        </template>

        <!-- Price -->
        <template
          v-if="
            sysField.attributeTypeId ===
            $store.state.productAttrs.attrTypes.price
          "
        >
          <v-tabs v-model="sysField.tab" class="transparent-tabs">
            <template v-for="(tab, tabIdx) in sysField.attributeLangList">
              <v-tab
                :key="`${sysField.name}-${sysFieldIdx}-tab-${tabIdx}`"
                :class="sysField.tab !== tabIdx && 'black--text'"
                active-class="primary--text"
                class="text-none font-weight-medium letter-spacing-normal fs-14"
                >{{ tab.name }}</v-tab
              >

              <v-tab-item
                :key="`${sysField.name}-${sysFieldIdx}-tab-item-${tabIdx}`"
              >
                <v-text-field
                  v-model="tab.value"
                  :error-messages="tab.errors"
                  :placeholder="sysField.name"
                  class="border-1 fs-14"
                  solo
                  flat
                  dense
                  type="number"
                  @input="tab.errors = []"
                ></v-text-field>
              </v-tab-item>
            </template>
          </v-tabs>
        </template>

        <!-- Number -->
        <template
          v-if="
            sysField.attributeTypeId ===
            $store.state.productAttrs.attrTypes.number
          "
        >
          <v-tabs v-model="sysField.tab" class="transparent-tabs">
            <template v-for="(tab, tabIdx) in sysField.attributeLangList">
              <v-tab
                :key="`${sysField.name}-${sysFieldIdx}-tab-${tabIdx}`"
                :class="sysField.tab !== tabIdx && 'black--text'"
                active-class="primary--text"
                class="text-none font-weight-medium letter-spacing-normal fs-14"
                >{{ tab.name }}</v-tab
              >

              <v-tab-item
                :key="`${sysField.name}-${sysFieldIdx}-tab-item-${tabIdx}`"
              >
                <v-text-field
                  v-model="tab.value"
                  :error-messages="tab.errors"
                  :placeholder="sysField.name"
                  class="border-1 fs-14"
                  solo
                  flat
                  dense
                  type="number"
                  @input="tab.errors = []"
                ></v-text-field>
              </v-tab-item>
            </template>
          </v-tabs>
        </template>

        <!-- Image -->
        <Files
          v-if="
            sysField.attributeTypeId ===
            $store.state.productAttrs.attrTypes.image
          "
          :id="`${sysField.name}-file-image-${sysFieldIdx}`"
          :ref="`${sysField.name}-file-image-${sysFieldIdx}`"
          :maxrequied="1"
          :errormsg="sysField.errors"
          @set_file="selectImage($event, sysField, sysFieldIdx)"
        />

        <!-- Video -->
        <FilesVideo
          v-if="
            sysField.attributeTypeId ===
            $store.state.productAttrs.attrTypes.video
          "
          :id="`${sysField.name}-file-video-${sysFieldIdx}`"
          :ref="`${sysField.name}-file-video-${sysFieldIdx}`"
          :required="sysField.errors && sysField.errors.length ? true : false"
          @set_file="selectVideo($event, sysField, sysFieldIdx)"
        />
      </v-col>

      <!-- Custom Attribute -->
      <v-col
        v-for="(customField, customFieldIdx) in customFields"
        :key="`field-${customField.name}-${customFieldIdx}`"
        cols="12"
      >
        <p
          class="fs-14 font-weight-bold"
          :class="
            customField.attributeTypeId ===
              $store.state.productAttrs.attrTypes.switch &&
            'd-flex align-center'
          "
        >
          {{ customField.name }}
          <span v-if="customField.required === 1" class="error--text ml-1">
            *
          </span>

          <!-- Switch -->
          <span class="d-inline-block ml-6">
            <v-switch
              v-if="
                customField.attributeTypeId ===
                $store.state.productAttrs.attrTypes.switch
              "
              v-model="customField.value"
              class="ma-0"
              color="#47bbff"
              :true-value="1"
              :false-value="0"
              inset
              hide-details
            />
          </span>
        </p>

        <!-- Đoạn văn bản ngắn -->
        <template
          v-if="
            customField.attributeTypeId ===
            $store.state.productAttrs.attrTypes.textField
          "
        >
          <v-tabs v-model="customField.tab" class="transparent-tabs">
            <template v-for="(tab, tabIdx) in customField.attributeLangList">
              <v-tab
                :key="`${customField.name}-${customFieldIdx}-tab-${tabIdx}`"
                :class="customField.tab !== tabIdx && 'black--text'"
                active-class="primary--text"
                class="text-none font-weight-medium letter-spacing-normal fs-14"
                >{{ tab.name }}</v-tab
              >

              <v-tab-item
                :key="`${customField.name}-${customFieldIdx}-tab-item-${tabIdx}`"
              >
                <v-text-field
                  v-model="tab.value"
                  :error-messages="tab.errors"
                  :placeholder="customField.name"
                  class="border-1 fs-14"
                  solo
                  flat
                  dense
                  @input="tab.errors = []"
                ></v-text-field>
              </v-tab-item>
            </template>
          </v-tabs>
        </template>

        <!-- Đoạn văn bản dài -->
        <template
          v-if="
            customField.attributeTypeId ===
            $store.state.productAttrs.attrTypes.textArea
          "
        >
          <v-tabs v-model="customField.tab" class="transparent-tabs">
            <template v-for="(tab, tabIdx) in customField.attributeLangList">
              <v-tab
                :key="`${customField.name}-${customFieldIdx}-tab-${tabIdx}`"
                :class="customField.tab !== tabIdx && 'black--text'"
                active-class="primary--text"
                class="text-none font-weight-medium letter-spacing-normal fs-14"
                >{{ tab.name }}</v-tab
              >

              <v-tab-item
                :key="`${customField.name}-${customFieldIdx}-tab-item-${tabIdx}`"
              >
                <vue-editor
                  id="vue-2-editor-fix-height-1"
                  v-model="tab.value"
                  use-custom-image-handler
                  @text-change="tab.errors = []"
                />
                <div
                  v-if="tab.errors && tab.errors.length"
                  class="v-messages theme--light error--text mt-1"
                  role="alert"
                >
                  <div class="v-messages__wrapper">
                    <div class="v-messages__message">Không được để trống</div>
                  </div>
                </div>
              </v-tab-item>
            </template>
          </v-tabs>
        </template>

        <!-- Datepicker -->
        <template
          v-if="
            customField.attributeTypeId ===
            $store.state.productAttrs.attrTypes.date
          "
        >
          <v-tabs v-model="customField.tab" class="transparent-tabs">
            <template v-for="(tab, tabIdx) in customField.attributeLangList">
              <v-tab
                :key="`${customField.name}-${customFieldIdx}-tab-${tabIdx}`"
                :class="customField.tab !== tabIdx && 'black--text'"
                active-class="primary--text"
                class="text-none font-weight-medium letter-spacing-normal fs-14"
                >{{ tab.name }}</v-tab
              >

              <v-tab-item
                :key="`${tab.name}-${customFieldIdx}-tab-item-${tabIdx}`"
              >
                <v-dialog
                  ref="dialog"
                  v-model="tab.modelIsOpened"
                  persistent
                  width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      :value="
                        $formatDate(
                          tab.value,
                          'YYYY-MM-DD',
                          'ddd DD/MM/YYYY',
                          true
                        )
                      "
                      :error-messages="tab.errors"
                      :placeholder="customField.name"
                      class="border-1 fs-14"
                      solo
                      flat
                      dense
                      readonly
                      @input="tab.errors = []"
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-date-picker v-model="tab.value" scrollable>
                    <v-spacer></v-spacer>
                    <v-btn
                      text
                      color="primary"
                      class="font-weight-bold"
                      @click="tab.modelIsOpened = false"
                    >
                      OK
                    </v-btn>
                  </v-date-picker>
                </v-dialog>
              </v-tab-item>
            </template>
          </v-tabs>
        </template>

        <!-- Datetime -->
        <template
          v-if="
            customField.attributeTypeId ===
            $store.state.productAttrs.attrTypes.dateTime
          "
        >
          <v-tabs v-model="customField.tab" class="transparent-tabs">
            <template v-for="(tab, tabIdx) in customField.attributeLangList">
              <v-tab
                :key="`${customField.name}-${customFieldIdx}-tab-${tabIdx}`"
                :class="customField.tab !== tabIdx && 'black--text'"
                active-class="primary--text"
                class="text-none font-weight-medium letter-spacing-normal fs-14"
                >{{ tab.name }}</v-tab
              >

              <v-tab-item
                :key="`${customField.name}-${customFieldIdx}-tab-item-${tabIdx}`"
              >
                <v-dialog
                  ref="dialog"
                  v-model="tab.modelIsOpened"
                  persistent
                  width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-row no-gutters>
                      <v-col cols="1">
                        <v-select
                          v-model="tab.hour"
                          :items="hours"
                          :error-messages="tab.hourErrors"
                          placeholder="Giờ"
                          class="border-1 fs-14"
                          solo
                          flat
                          dense
                          @input="tab.hourErrors = []"
                        ></v-select>
                      </v-col>
                      <v-col cols="1">
                        <v-select
                          v-model="tab.minute"
                          :items="minutes"
                          :error-messages="tab.minuteErrors"
                          placeholder="Phút"
                          class="border-1 fs-14"
                          solo
                          flat
                          dense
                          @input="tab.minuteErrors = []"
                        ></v-select>
                      </v-col>
                      <v-col cols="10">
                        <v-text-field
                          :value="
                            $formatDate(
                              tab.value,
                              'YYYY-MM-DD',
                              'ddd DD/MM/YYYY',
                              true
                            )
                          "
                          :error-messages="tab.errors"
                          placeholder="Ngày"
                          class="border-1 fs-14 rounded-r"
                          solo
                          flat
                          dense
                          readonly
                          @input="tab.errors = []"
                          v-on="on"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                  </template>
                  <v-date-picker v-model="tab.value" scrollable>
                    <v-spacer></v-spacer>
                    <v-btn
                      text
                      color="primary"
                      class="font-weight-bold"
                      @click="tab.modelIsOpened = false"
                    >
                      OK
                    </v-btn>
                  </v-date-picker>
                </v-dialog>
              </v-tab-item>
            </template>
          </v-tabs>
        </template>

        <!-- Select -->
        <template
          v-if="
            customField.attributeTypeId ===
            $store.state.productAttrs.attrTypes.select
          "
        >
          <v-tabs v-model="customField.tab" class="transparent-tabs">
            <template v-for="(tab, tabIdx) in customField.attributeLangList">
              <v-tab
                :key="`${customField.name}-${customFieldIdx}-tab-${tabIdx}`"
                :class="customField.tab !== tabIdx && 'black--text'"
                active-class="primary--text"
                class="text-none font-weight-medium letter-spacing-normal fs-14"
                >{{ tab.name }}</v-tab
              >

              <v-tab-item
                :key="`${customField.name}-${customFieldIdx}-tab-item-${tabIdx}`"
              >
                <v-select
                  v-model="tab.value"
                  :items="tab.optionValues"
                  item-text="option"
                  item-value="id"
                  :error-messages="tab.errors"
                  :placeholder="customField.name"
                  class="border-1 fs-14"
                  solo
                  flat
                  dense
                  @input="tab.errors = []"
                >
                </v-select>
              </v-tab-item>
            </template>
          </v-tabs>
        </template>

        <!-- Multi Select -->
        <template
          v-if="
            customField.attributeTypeId ===
            $store.state.productAttrs.attrTypes.multiSelect
          "
        >
          <v-tabs v-model="customField.tab" class="transparent-tabs">
            <template v-for="(tab, tabIdx) in customField.attributeLangList">
              <v-tab
                :key="`${customField.name}-${customFieldIdx}-tab-${tabIdx}`"
                :class="customField.tab !== tabIdx && 'black--text'"
                active-class="primary--text"
                class="text-none font-weight-medium letter-spacing-normal fs-14"
                >{{ tab.name }}</v-tab
              >

              <v-tab-item
                :key="`${customField.name}-${customFieldIdx}-tab-item-${tabIdx}`"
              >
                <v-select
                  v-model="tab.value"
                  :items="tab.optionValues"
                  item-text="option"
                  item-value="id"
                  :error-messages="tab.errors"
                  :placeholder="customField.name"
                  class="border-1 fs-14"
                  solo
                  flat
                  dense
                  multiple
                  @input="tab.errors = []"
                >
                </v-select>
              </v-tab-item>
            </template>
          </v-tabs>
        </template>

        <!-- Radio -->
        <template
          v-if="
            customField.attributeTypeId ===
            $store.state.productAttrs.attrTypes.radio
          "
        >
          <v-tabs v-model="customField.tab" class="transparent-tabs">
            <template v-for="(tab, tabIdx) in customField.attributeLangList">
              <v-tab
                :key="`${customField.name}-${customFieldIdx}-tab-${tabIdx}`"
                :class="customField.tab !== tabIdx && 'black--text'"
                active-class="primary--text"
                class="text-none font-weight-medium letter-spacing-normal fs-14"
                >{{ tab.name }}</v-tab
              >

              <v-tab-item
                :key="`${customField.name}-${customFieldIdx}-tab-item-${tabIdx}`"
              >
                <v-radio-group
                  v-model="tab.value"
                  :error-messages="tab.errors"
                  row
                >
                  <v-radio
                    v-for="(option, optionIdx) in tab.optionValues"
                    :key="`${customField.name}-${option.id}-${option.option}-${optionIdx}`"
                    :label="option.option"
                    :value="option.id"
                  ></v-radio>
                </v-radio-group>
              </v-tab-item>
            </template>
          </v-tabs>
        </template>

        <!-- Checkbox -->
        <template
          v-if="
            customField.attributeTypeId ===
            $store.state.productAttrs.attrTypes.checkbox
          "
        >
          <v-tabs v-model="customField.tab" class="transparent-tabs">
            <template v-for="(tab, tabIdx) in customField.attributeLangList">
              <v-tab
                :key="`${customField.name}-${customFieldIdx}-tab-${tabIdx}`"
                :class="customField.tab !== tabIdx && 'black--text'"
                active-class="primary--text"
                class="text-none font-weight-medium letter-spacing-normal fs-14"
                >{{ tab.name }}</v-tab
              >

              <v-tab-item
                :key="`${customField.name}-${customFieldIdx}-tab-item-${tabIdx}`"
              >
                <v-radio-group
                  :value="tab.value"
                  :error-messages="tab.errors"
                  multiple
                  row
                >
                  <v-radio
                    v-for="(option, optionIdx) in tab.optionValues"
                    :key="`${customField.name}-${option.id}-${option.option}-${optionIdx}`"
                    :label="option.option"
                    :value="option.id"
                    off-icon="$checkboxOff"
                    on-icon="$checkboxOn"
                    @click="toggleMultipleRadio(option.id, tab.value)"
                  ></v-radio>
                </v-radio-group>
              </v-tab-item>
            </template>
          </v-tabs>
        </template>

        <!-- Price -->
        <template
          v-if="
            customField.attributeTypeId ===
            $store.state.productAttrs.attrTypes.price
          "
        >
          <v-tabs v-model="customField.tab" class="transparent-tabs">
            <template v-for="(tab, tabIdx) in customField.attributeLangList">
              <v-tab
                :key="`${customField.name}-${customFieldIdx}-tab-${tabIdx}`"
                :class="customField.tab !== tabIdx && 'black--text'"
                active-class="primary--text"
                class="text-none font-weight-medium letter-spacing-normal fs-14"
                >{{ tab.name }}</v-tab
              >

              <v-tab-item
                :key="`${customField.name}-${customFieldIdx}-tab-item-${tabIdx}`"
              >
                <v-text-field
                  v-model="tab.value"
                  :error-messages="tab.errors"
                  :placeholder="customField.name"
                  class="border-1 fs-14"
                  solo
                  flat
                  dense
                  type="number"
                  @input="tab.errors = []"
                ></v-text-field>
              </v-tab-item>
            </template>
          </v-tabs>
        </template>

        <!-- Number -->
        <template
          v-if="
            customField.attributeTypeId ===
            $store.state.productAttrs.attrTypes.number
          "
        >
          <v-tabs v-model="customField.tab" class="transparent-tabs">
            <template v-for="(tab, tabIdx) in customField.attributeLangList">
              <v-tab
                :key="`${customField.name}-${customFieldIdx}-tab-${tabIdx}`"
                :class="customField.tab !== tabIdx && 'black--text'"
                active-class="primary--text"
                class="text-none font-weight-medium letter-spacing-normal fs-14"
                >{{ tab.name }}</v-tab
              >

              <v-tab-item
                :key="`${customField.name}-${customFieldIdx}-tab-item-${tabIdx}`"
              >
                <v-text-field
                  v-model="tab.value"
                  :error-messages="tab.errors"
                  :placeholder="customField.name"
                  class="border-1 fs-14"
                  solo
                  flat
                  dense
                  type="number"
                  @input="tab.errors = []"
                ></v-text-field>
              </v-tab-item>
            </template>
          </v-tabs>
        </template>

        <!-- Image -->
        <Files
          v-if="
            customField.attributeTypeId ===
            $store.state.productAttrs.attrTypes.image
          "
          :id="`${customField.name}-file-image-${customFieldIdx}`"
          :ref="`${customField.name}-file-image-${customFieldIdx}`"
          :maxrequied="1"
          :errormsg="customField.errors"
          @set_file="selectImage($event, customField, customFieldIdx)"
        />

        <!-- Video -->
        <FilesVideo
          v-if="
            customField.attributeTypeId ===
            $store.state.productAttrs.attrTypes.video
          "
          :id="`${customField.name}-file-video-${customFieldIdx}`"
          :ref="`${customField.name}-file-video-${customFieldIdx}`"
          :required="
            customField.errors && customField.errors.length ? true : false
          "
          @set_file="selectVideo($event, customField, customFieldIdx)"
        />
      </v-col>
      <v-footer inset app>
        <v-row>
          <v-spacer></v-spacer>
          <v-btn
            class="text-none white--text mr-3"
            color="error"
            depressed
            width="90"
            @click="confirmNavigationIsOpened = true"
          >
            Hủy
          </v-btn>
          <v-btn
            class="text-none white--text"
            color="#47BBFF"
            dark
            depressed
            width="90"
            @click="validate"
          >
            Lưu
          </v-btn>
        </v-row>
      </v-footer>

      <yes-no-alert
        :open="confirmNavigationIsOpened"
        alert-msg="Bạn chắc chắn muốn thoát không?"
        @toggle="confirmNavigationIsOpened = !confirmNavigationIsOpened"
        @OK="navigationConfirmed"
      />

      <yes-no-alert
        :open="confirmResetDialogisOpened"
        alert-msg="Bạn chắc chắn muốn đặt lại không?"
        @toggle="confirmResetDialogisOpened = !confirmResetDialogisOpened"
        @OK="getProdAttrSetFields"
      />
    </v-row>
    <v-row v-else class="align-center justify-center">
      <v-col cols="12" align="center">
        <v-progress-circular
          :indeterminate="!$isServer"
          :value="100"
          size="120"
          color="primary"
        >
          Xin chờ...
        </v-progress-circular>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import Files from '~/components/Selected/Files.vue'
import FilesVideo from '~/components/Selected/FileVideo.vue'
export default {
  components: {
    Files,
    FilesVideo,
  },
  props: {
    prodAttrSet: {
      type: Number,
      required: true,
    },
  },

  data() {
    const hours = []
    for (let index = 0; index < 24; index++) {
      hours.push(index < 10 ? `0${index}` : `${index}`)
    }

    const minutes = []
    for (let index = 0; index < 60; index++) {
      minutes.push(index < 10 ? `0${index}` : `${index}`)
    }
    return {
      systemFields: [],
      customFields: [],
      attributeLangList: [],
      hours,
      minutes,
      hydrated: false,
      confirmNavigationIsOpened: false,
      confirmResetDialogisOpened: false,
      sku: null,
    }
  },
  watch: {
    '$store.state.app.change_back'(value) {
      this.confirmNavigationIsOpened = true
    },
  },
  mounted() {
    this.$log.debug('[Props prodAttrSet]', this.prodAttrSet)
    this.getSku()
  },
  methods: {
    // Hàm generate tab đa ngôn ngữ
    generateTabs() {
      for (
        let index = 0;
        index < this.$store.state.app.languages.length;
        index++
      ) {
        const language = this.$store.state.app.languages[index]
        this.attributeLangList.push({
          ...language,
        })
      }

      this.getProdAttrSetFields()
    },

    getProdAttrSetFields() {
      this.hydrated = false
      this.$store
        .dispatch('tourismProduct/getProdAttrSetFields', {
          attributeSetId: this.prodAttrSet,
        })
        .then((response) => {
          if (!response.error) {
            this.hydrateData(response.data.data)
          }
        })
    },

    getSku() {
      this.$store.dispatch('tourismProduct/getSku').then((response) => {
        if (!response.error) {
          this.sku = response.data.data
          this.generateTabs()
        }
      })
    },

    hydrateData(rawApiData) {
      const attrTypes = { ...this.$store.state.productAttrs.attrTypes }
      this.systemFields = rawApiData.sysAttribute.reduce((acc, cur, idx) => {
        if (cur.attributeTypeId === attrTypes.switch) {
          cur.value = 1
        } else if (
          cur.attributeTypeId === attrTypes.image ||
          cur.attributeTypeId === attrTypes.video
        ) {
          cur.value = null
        } else {
          const attributeLangList = []
          for (let index = 0; index < this.attributeLangList.length; index++) {
            const lang = this.attributeLangList[index]
            const attributeLang = {
              ...lang,
              errors: [],
            }
            if (
              cur.attributeTypeId === attrTypes.textField ||
              cur.attributeTypeId === attrTypes.textArea
            ) {
              attributeLang.value = ''
            } else if (cur.attributeTypeId === attrTypes.date) {
              attributeLang.value = null
            } else if (cur.attributeTypeId === attrTypes.dateTime) {
              attributeLang.hour = null
              attributeLang.minute = null
              attributeLang.value = null
            } else if (cur.attributeTypeId === attrTypes.select) {
              attributeLang.value = null
              attributeLang.optionValues = cur.optionValues.filter(
                (e) => e.langId === lang.id
              )
            } else if (
              cur.attributeTypeId === attrTypes.multiSelect ||
              cur.attributeTypeId === attrTypes.checkbox
            ) {
              attributeLang.value = []
              attributeLang.optionValues = cur.optionValues.filter(
                (e) => e.langId === lang.id
              )
            } else if (cur.attributeTypeId === attrTypes.radio) {
              attributeLang.value = null
              attributeLang.optionValues = cur.optionValues.filter(
                (e) => e.langId === lang.id
              )
            } else if (
              cur.attributeTypeId === attrTypes.price ||
              cur.attributeTypeId === attrTypes.number
            ) {
              attributeLang.value = null
            }

            attributeLangList.push(attributeLang)
          }
          cur.attributeLangList = attributeLangList
          cur.tab = 0
        }

        acc.push(cur)
        return acc
      }, [])
      this.customFields = rawApiData.customAttribute.reduce((acc, cur, idx) => {
        if (cur.attributeTypeId === attrTypes.switch) {
          cur.value = 1
        } else if (
          cur.attributeTypeId === attrTypes.image ||
          cur.attributeTypeId === attrTypes.video
        ) {
          cur.value = null
        } else {
          const attributeLangList = []
          for (let index = 0; index < this.attributeLangList.length; index++) {
            const lang = this.attributeLangList[index]
            const attributeLang = {
              ...lang,
              errors: [],
            }
            if (
              cur.attributeTypeId === attrTypes.textField ||
              cur.attributeTypeId === attrTypes.textArea
            ) {
              attributeLang.value = ''
            } else if (cur.attributeTypeId === attrTypes.date) {
              attributeLang.value = null
            } else if (cur.attributeTypeId === attrTypes.dateTime) {
              attributeLang.hour = null
              attributeLang.minute = null
              attributeLang.value = null
            } else if (cur.attributeTypeId === attrTypes.select) {
              attributeLang.value = null
              attributeLang.optionValues = cur.optionValues.filter(
                (e) => e.langId === lang.id
              )
            } else if (
              cur.attributeTypeId === attrTypes.multiSelect ||
              cur.attributeTypeId === attrTypes.checkbox
            ) {
              attributeLang.value = []
              attributeLang.optionValues = cur.optionValues.filter(
                (e) => e.langId === lang.id
              )
            } else if (cur.attributeTypeId === attrTypes.radio) {
              attributeLang.value = null
              attributeLang.optionValues = cur.optionValues.filter(
                (e) => e.langId === lang.id
              )
            } else if (
              cur.attributeTypeId === attrTypes.price ||
              cur.attributeTypeId === attrTypes.number
            ) {
              attributeLang.value = null
            }

            attributeLangList.push(attributeLang)
          }
          cur.attributeLangList = attributeLangList
          cur.tab = 0
        }

        acc.push(cur)
        return acc
      }, [])
      this.hydrated = true
    },

    navigationConfirmed() {
      this.$emit('toggle')
    },

    toggleMultipleRadio(id, array) {
      if (!array) {
        array = [id]
      } else if (array.includes(id)) {
        const index = array.findIndex((e) => e === id)
        if (index > -1) {
          array.splice(index, 1)
        }
      } else {
        array = array.push(id)
      }
    },

    selectImage(event, customField, customFieldIdx) {
      this.$log.debug(event, customField, customFieldIdx)
      customField.value = event
    },

    selectVideo($event, customField, customFieldIdx) {
      this.$log.debug(event, customField, customFieldIdx)
      customField.value = event
    },

    validate() {
      let hasErrors = false
      const attrTypes = { ...this.$store.state.productAttrs.attrTypes }
      const systemFields = [...this.systemFields]
      const customFields = [...this.customFields]

      for (let index = 0; index < systemFields.length; index++) {
        const dataField = systemFields[index]
        const isRequired = dataField.required
        const fieldType = dataField.attributeTypeId
        if (
          fieldType !== attrTypes.switch &&
          fieldType !== attrTypes.image &&
          fieldType !== attrTypes.video
        ) {
          let lastInvalidTab = null
          for (
            let langIdx = 0;
            langIdx < dataField.attributeLangList.length;
            langIdx++
          ) {
            const lang = dataField.attributeLangList[langIdx]
            if (
              fieldType === attrTypes.textField ||
              fieldType === attrTypes.textArea ||
              fieldType === attrTypes.date
            ) {
              if (isRequired) {
                if (!lang.value || (lang.value && !lang.value.trim())) {
                  hasErrors = true
                  lang.errors = ['Không được để trống']
                  if (lastInvalidTab === null) {
                    lastInvalidTab = langIdx
                  }
                }
              }
            }

            if (fieldType === attrTypes.dateTime) {
              if (isRequired) {
                if (!lang.value || (lang.value && !lang.value.trim())) {
                  lang.errors = ['Không được để trống']
                  hasErrors = true
                  if (lastInvalidTab === null) {
                    lastInvalidTab = langIdx
                  }
                }

                if (!lang.hour || (lang.hour && !lang.hour.trim())) {
                  hasErrors = true
                  lang.hourErrors = ['Không được để trống']
                  if (lastInvalidTab === null) {
                    lastInvalidTab = langIdx
                  }
                }

                if (!lang.minute || (lang.minute && !lang.minute.trim())) {
                  hasErrors = true
                  lang.minuteErrors = ['Không được để trống']
                  if (lastInvalidTab === null) {
                    lastInvalidTab = langIdx
                  }
                }
              }
            }

            if (
              fieldType === attrTypes.select ||
              fieldType === attrTypes.radio
            ) {
              if (isRequired) {
                if (lang.value !== 0 && !lang.value) {
                  lang.errors = ['Không được để trống']
                  hasErrors = true
                  if (lastInvalidTab === null) {
                    lastInvalidTab = langIdx
                  }
                }
              }
            }

            if (
              fieldType === attrTypes.multiSelect ||
              fieldType === attrTypes.checkbox
            ) {
              if (isRequired) {
                if (!lang.value.length) {
                  lang.errors = ['Không được để trống']
                  hasErrors = true
                  if (lastInvalidTab === null) {
                    lastInvalidTab = langIdx
                  }
                }
              }
            }

            if (
              fieldType === attrTypes.price ||
              fieldType === attrTypes.number
            ) {
              if (isRequired) {
                if (!lang.value || (lang.value && !lang.value.trim())) {
                  lang.errors = ['Không được để trống']
                  hasErrors = true
                  if (lastInvalidTab === null) {
                    lastInvalidTab = langIdx
                  }
                }
              }

              if (lang.value) {
                if (isNaN(lang.value)) {
                  lang.errors = ['Vui lòng nhập số']
                  hasErrors = true
                  if (lastInvalidTab === null) {
                    lastInvalidTab = langIdx
                  }
                } else if (
                  Number(lang.value) < 0 &&
                  fieldType !== attrTypes.number
                ) {
                  lang.errors = ['Vui lòng nhập số lớn hơn 0']
                  hasErrors = true
                  if (lastInvalidTab === null) {
                    lastInvalidTab = langIdx
                  }
                }
              }
            }
          }

          if (lastInvalidTab) {
            dataField.tab = lastInvalidTab
          }
        }

        if (fieldType === attrTypes.image || fieldType === attrTypes.video) {
          if (isRequired) {
            if (!dataField.value) {
              dataField.errors = 'Không được để trống'
              hasErrors = true
            }
          }
        }
      }

      for (let index = 0; index < customFields.length; index++) {
        const dataField = customFields[index]
        const isRequired = dataField.required
        const fieldType = dataField.attributeTypeId
        if (
          fieldType !== attrTypes.switch &&
          fieldType !== attrTypes.image &&
          fieldType !== attrTypes.video
        ) {
          let lastInvalidTab = null
          for (
            let langIdx = 0;
            langIdx < dataField.attributeLangList.length;
            langIdx++
          ) {
            const lang = dataField.attributeLangList[langIdx]
            if (
              fieldType === attrTypes.textField ||
              fieldType === attrTypes.textArea ||
              fieldType === attrTypes.date
            ) {
              if (isRequired) {
                if (!lang.value || (lang.value && !lang.value.trim())) {
                  hasErrors = true
                  lang.errors = ['Không được để trống']
                  if (lastInvalidTab === null) {
                    lastInvalidTab = langIdx
                  }
                }
              }
            }

            if (fieldType === attrTypes.dateTime) {
              if (isRequired) {
                if (!lang.value || (lang.value && !lang.value.trim())) {
                  lang.errors = ['Không được để trống']
                  hasErrors = true
                  if (lastInvalidTab === null) {
                    lastInvalidTab = langIdx
                  }
                }

                if (!lang.hour || (lang.hour && !lang.hour.trim())) {
                  hasErrors = true
                  lang.hourErrors = ['Không được để trống']
                  if (lastInvalidTab === null) {
                    lastInvalidTab = langIdx
                  }
                }

                if (!lang.minute || (lang.minute && !lang.minute.trim())) {
                  hasErrors = true
                  lang.minuteErrors = ['Không được để trống']
                  if (lastInvalidTab === null) {
                    lastInvalidTab = langIdx
                  }
                }
              }
            }

            if (
              fieldType === attrTypes.select ||
              fieldType === attrTypes.radio
            ) {
              if (isRequired) {
                if (lang.value !== 0 && !lang.value) {
                  lang.errors = ['Không được để trống']
                  hasErrors = true
                  if (lastInvalidTab === null) {
                    lastInvalidTab = langIdx
                  }
                }
              }
            }

            if (
              fieldType === attrTypes.multiSelect ||
              fieldType === attrTypes.checkbox
            ) {
              if (isRequired) {
                if (!lang.value.length) {
                  lang.errors = ['Không được để trống']
                  hasErrors = true
                  if (lastInvalidTab === null) {
                    lastInvalidTab = langIdx
                  }
                }
              }
            }

            if (
              fieldType === attrTypes.price ||
              fieldType === attrTypes.number
            ) {
              if (isRequired) {
                if (!lang.value || (lang.value && !lang.value.trim())) {
                  lang.errors = ['Không được để trống']
                  hasErrors = true
                  if (lastInvalidTab === null) {
                    lastInvalidTab = langIdx
                  }
                }
              }

              if (lang.value) {
                if (isNaN(lang.value)) {
                  lang.errors = ['Vui lòng nhập số']
                  hasErrors = true
                  if (lastInvalidTab === null) {
                    lastInvalidTab = langIdx
                  }
                } else if (
                  Number(lang.value) < 0 &&
                  fieldType !== attrTypes.number
                ) {
                  lang.errors = ['Vui lòng nhập số lớn hơn 0']
                  hasErrors = true
                  if (lastInvalidTab === null) {
                    lastInvalidTab = langIdx
                  }
                }
              }
            }
          }

          if (lastInvalidTab) {
            dataField.tab = lastInvalidTab
          }
        }

        if (fieldType === attrTypes.image || fieldType === attrTypes.video) {
          if (isRequired) {
            if (!dataField.value) {
              dataField.errors = 'Không được để trống'
              hasErrors = true
            }
          }
        }
      }

      this.systemFields = systemFields
      this.customFields = customFields

      if (!hasErrors) {
        // ... call API
        this.addTourismProduct()
      }
    },

    addTourismProduct() {
      const data = {
        // sku: this.sku,
        // attributeSetId: this.prodAttrSet,
        // price: 1000,
        // enterpriseId: null,
        // lon: 3204,
        // lat: 127,
        // currencyId: 1,
        // productCategoryIds: [1, 2, 3, 4, 5],
      }
      this.$store
        .dispatch('tourismProduct/addTourismProduct', data)
        .then((response) => {
          if (!response.error) {
            this.$showSuccess(this, 'Thêm mới sản phẩm du lịch thành công')
            this.$emit('success')
          }
        })
    },
  },
}
</script>

<style lang="scss">
.add-tourism-prod {
  .transparent-tabs {
    .v-tabs-bar,
    .v-tabs-items {
      background: transparent !important;
    }
  }
  p {
    margin: 0 !important;
  }
}
</style>
