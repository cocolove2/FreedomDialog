package com.cocolove2.library_freedomdialog.default_effects;


import com.cocolove2.library_freedomdialog.default_effects.BaseAnimatorEffects;
import com.cocolove2.library_freedomdialog.default_effects.FadeIn;
import com.cocolove2.library_freedomdialog.default_effects.Fall;
import com.cocolove2.library_freedomdialog.default_effects.FlipH;
import com.cocolove2.library_freedomdialog.default_effects.FlipV;
import com.cocolove2.library_freedomdialog.default_effects.NewsPaper;

/*
 * Copyright 2014 litao
 * https://github.com/sd6352051/NiftyDialogEffects
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public enum Effectstype {

    Fadein(FadeIn.class),
    Falling(Fall.class),
    Newspager(NewsPaper.class),
    Fliph(FlipH.class),
    Flipv(FlipV.class);

    private Class<? extends BaseAnimatorEffects> effectsClazz;

    private Effectstype(Class<? extends BaseAnimatorEffects> mclass) {
        effectsClazz = mclass;
    }

    public BaseAnimatorEffects getAnimator() {
        BaseAnimatorEffects bEffects = null;
        try {
            bEffects = effectsClazz.newInstance();
        } catch (ClassCastException e) {
            throw new Error("Can not init animatorClazz instance");
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            throw new Error("Can not init animatorClazz instance");
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            throw new Error("Can not init animatorClazz instance");
        }
        return bEffects;
    }
}
